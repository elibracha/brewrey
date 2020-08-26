package com.github.elibracha.brewrey.services;

import com.github.elibracha.brewrey.web.dtos.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID id);

    UUID createBeer(BeerDto beer);

    UUID updateBeer(UUID id, BeerDto beer);

    void deleteBeer(UUID id);
}
