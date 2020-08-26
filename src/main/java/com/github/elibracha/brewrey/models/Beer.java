package com.github.elibracha.brewrey.models;

import com.github.elibracha.brewrey.web.dtos.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;

    public Beer(BeerDto beerDto) {
        this.beerName = beerDto.getBeerName();
        this.beerStyle = beerDto.getBeerStyle();
        this.upc = beerDto.getUpc();
    }
}
