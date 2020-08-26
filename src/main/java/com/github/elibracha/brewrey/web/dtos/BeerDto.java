package com.github.elibracha.brewrey.web.dtos;

import com.github.elibracha.brewrey.models.Beer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;

    public BeerDto(Beer beer) {
        this.id = beer.getId();
        this.beerName = beer.getBeerName();
        this.beerStyle = beer.getBeerStyle();
        this.upc = beer.getUpc();
    }
}
