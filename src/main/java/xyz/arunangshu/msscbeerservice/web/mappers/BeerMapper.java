package xyz.arunangshu.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;
import xyz.arunangshu.msscbeerservice.domain.Beer;
import xyz.arunangshu.msscbeerservice.web.model.BeerDto;

@Mapper(uses = {DateTimeMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
