package com.github.elibracha.brewrey.services;

import com.github.elibracha.brewrey.domain.Beer;
import com.github.elibracha.brewrey.repositories.BeerRepository;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
import com.github.elibracha.brewrey.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private BeerRepository beerRepository;
    private BeerMapper mapper;

    @Override
    public List<BeerDto> getBeers(int page, int size) {
        return beerRepository.findAll(PageRequest.of(page, size, Sort.by("beerName")))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        Optional<Beer> beerOptional = beerRepository.findById(beerId);
        Beer beer = beerOptional.orElseThrow(
                () -> new EntityNotFoundException(String.format("Beer with id %s not found", beerId))
        );
        return mapper.toDto(beer);
    }

    @Override
    public UUID createBeer(BeerDto beerDto) {
        Beer beer = beerRepository.save(mapper.fromDto(beerDto));
        return beer.getId();
    }

    @Override
    public UUID updateBeer(UUID beerId, BeerDto beerDto) {
        Optional<Beer> beerOptional = beerRepository.findById(beerId);
        Beer beer = beerOptional.orElseThrow(
                () -> new EntityNotFoundException(String.format("Beer with id %s not found", beerId))
        );
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle());
        beer.setUpc(beerDto.getUpc());
        beer.setPrice(beerDto.getPrice());

        beerRepository.save(beer);
        return beer.getId();
    }

    @Override
    public void deleteBeer(UUID id) {
        beerRepository.deleteById(id);
    }
}
