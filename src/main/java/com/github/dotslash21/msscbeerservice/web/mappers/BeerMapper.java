package com.github.dotslash21.msscbeerservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import com.github.dotslash21.msscbeerservice.domain.Beer;
import com.github.dotslash21.msscbeerservice.web.model.BeerDto;

@Mapper(uses = {DateTimeMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
