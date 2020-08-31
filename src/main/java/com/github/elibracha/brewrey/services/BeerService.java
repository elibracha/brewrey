package com.github.elibracha.brewrey.services;

import com.github.elibracha.brewrey.web.dtos.BeerDto;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<BeerDto> getBeers(int page, int size);

    BeerDto getBeerById(UUID id);

    UUID createBeer(BeerDto beer);

    UUID updateBeer(UUID id, BeerDto beer);

    void deleteBeer(UUID id);
}
