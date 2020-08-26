package com.github.elibracha.brewrey.web.mappers;

import com.github.elibracha.brewrey.domain.Beer;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(uses = {DateMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface BeerMapper {

    BeerDto toDto(Beer beer);

    Beer fromDto(BeerDto beerDto);
}
