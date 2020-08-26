package com.github.elibracha.brewrey.services;

import com.github.elibracha.brewrey.repositories.BeerRepository;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
import com.github.elibracha.brewrey.web.mappers.BeerMapper;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {

    private BeerRepository beerRepository;
    private BeerMapper mapper;

    public BeerServiceImpl(BeerRepository beerRepository, BeerMapper mapper) {
        this.beerRepository = beerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BeerDto> getBeers(int page, int size) {
        return beerRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        val beer = beerRepository.findById(beerId).orElseThrow(EntityNotFoundException::new);
        return mapper.toDto(beer);
    }

    @Override
    public UUID createBeer(BeerDto beerDto) {
        if (beerRepository.findByUpc(beerDto.getUpc()).isPresent()) throw new ValidationException();
        val beer = beerRepository.save(mapper.fromDto(beerDto));
        return beer.getId();
    }

    @Override
    public UUID updateBeer(UUID beerId, BeerDto beerDto) {
        val beer = beerRepository.findById(beerId).orElseThrow(EntityNotFoundException::new);

        mapper.merge(beer, beerDto);
        beerRepository.save(beer);

        return beer.getId();
    }

    @Override
    public void deleteBeer(UUID beerId) {
        val beer = beerRepository.findById(beerId).orElseThrow(EntityNotFoundException::new);
        beerRepository.delete(beer);
    }

}
