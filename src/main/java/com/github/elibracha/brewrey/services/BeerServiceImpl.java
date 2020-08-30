package com.github.elibracha.brewrey.services;

import com.github.elibracha.brewrey.config.JmsConfig;
import com.github.elibracha.brewrey.domain.events.EventType;
import com.github.elibracha.brewrey.providers.ExceptionProvider;
import com.github.elibracha.brewrey.repositories.BeerRepository;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
import com.github.elibracha.brewrey.web.mappers.BeerMapper;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper mapper;

    @Override
    @Cacheable(cacheNames = "BeerListCache")
    public List<BeerDto> getBeers(int page, int size) {
        return beerRepository.findAll(PageRequest.of(page, size, Sort.by("beerName").descending()))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "BeerCache", key = "#beerId")
    public BeerDto getBeerById(UUID beerId) {
        val beer = beerRepository.findById(beerId).orElseThrow(ExceptionProvider.ENTITY_NOT_FOUND_ERROR_SUPPLER);
        return mapper.toDto(beer);
    }

    @Override
    public UUID createBeer(BeerDto beerDto) {
        beerRepository.findByUpc(beerDto.getUpc()).ifPresent(ExceptionProvider.UPC_FOUND_ERROR_CONSUMER);
        val beer = beerRepository.save(mapper.fromDto(beerDto));

        val dto = mapper.toDto(beer);
        Optional.of(mapper.fromDtoToMessage(dto, EventType.BEER_CREATE_EVENT)).ifPresent(msg ->
                jmsTemplate.convertAndSend(JmsConfig.CREATE_TOPIC, msg)
        );
        return beer.getId();
    }

    @Override
    public UUID updateBeer(UUID beerId, BeerDto beerDto) {
        val beer = beerRepository.findById(beerId).orElseThrow(ExceptionProvider.ENTITY_NOT_FOUND_ERROR_SUPPLER);

        mapper.merge(beer, beerDto);
        beerRepository.save(beer);

        val dto = mapper.toDto(beer);
        Optional.of(mapper.fromDtoToMessage(dto, EventType.BEER_UPDATE_EVENT)).ifPresent(msg ->
                jmsTemplate.convertAndSend(JmsConfig.UPDATE_TOPIC, msg)
        );
        return beer.getId();
    }

    @Override
    public void deleteBeer(UUID beerId) {
        val beer = beerRepository.findById(beerId).orElseThrow(ExceptionProvider.ENTITY_NOT_FOUND_ERROR_SUPPLER);
        beerRepository.delete(beer);
    }
}
