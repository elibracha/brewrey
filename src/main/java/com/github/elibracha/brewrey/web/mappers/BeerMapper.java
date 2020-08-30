package com.github.elibracha.brewrey.web.mappers;

import com.github.elibracha.brewrey.domain.Beer;
import com.github.elibracha.brewrey.domain.events.BeerEvent;
import com.github.elibracha.brewrey.domain.events.EventType;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.UUID;


@Mapper(uses = {DateMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface BeerMapper {

    BeerDto toDto(Beer beer);

    Beer fromDto(BeerDto beerDto);

    default BeerEvent fromDtoToMessage(Beer beer, EventType type) {
        return BeerEvent.builder()
                .messageId(UUID.randomUUID())
                .beerId(beer.getId())
                .type(type)
                .content(beer)
                .build();
    }

    default void merge(Beer beer, BeerDto beerDto) {
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
    }

}
