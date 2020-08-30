package com.github.elibracha.brewrey.web.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.elibracha.brewrey.domain.Beer;
import com.github.elibracha.brewrey.domain.events.BeerCreatedEvent;
import com.github.elibracha.brewrey.domain.events.BeerEvent;
import com.github.elibracha.brewrey.domain.events.BeerUpdateEvent;
import com.github.elibracha.brewrey.domain.events.EventType;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.UUID;


@Mapper(uses = {DateMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface BeerMapper {

    BeerDto toDto(Beer beer);

    Beer fromDto(BeerDto beerDto);

    default BeerEvent fromDtoToMessage(BeerDto beerDto, EventType type) {
        String content = null;

        try {
            content = new ObjectMapper().writeValueAsString(beerDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        switch (type) {
            case BEER_UPDATE_EVENT:
                return BeerUpdateEvent.builder()
                        .id(UUID.randomUUID())
                        .type(type)
                        .content(content)
                        .build();
            case BEER_CREATE_EVENT:
                return BeerCreatedEvent.builder()
                        .id(UUID.randomUUID())
                        .type(type)
                        .content(content)
                        .build();
        }

        return null;
    }

    default void merge(Beer beer, BeerDto beerDto) {
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
    }

}
