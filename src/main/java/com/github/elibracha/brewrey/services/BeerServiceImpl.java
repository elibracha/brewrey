package com.github.elibracha.brewrey.services;

import com.github.elibracha.brewrey.models.Beer;
import com.github.elibracha.brewrey.repositories.BeerRepository;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {

    private BeerRepository beerRepository;

    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public List<BeerDto> getBeers(int page, int size) {
        return beerRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(BeerDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(beerId).build();
    }

    @Override
    public UUID createBeer(BeerDto beerDto) {
        Beer beer = beerRepository.save(new Beer(beerDto));
        return beer.getId();
    }

    @Override
    public UUID updateBeer(UUID id, BeerDto beerDto) {
        return id;
    }

    @Override
    public void deleteBeer(UUID id) {

    }
}
