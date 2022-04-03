package xyz.arunangshu.msscbeerservice.services;

import org.springframework.data.domain.PageRequest;
import xyz.arunangshu.msscbeerservice.web.model.BeerDto;
import xyz.arunangshu.msscbeerservice.web.model.BeerPagedList;
import xyz.arunangshu.msscbeerservice.web.model.BeerStyleEnum;

import java.util.UUID;

public interface BeerService {

    BeerPagedList listBeers(
            String beerName,
            BeerStyleEnum beerStyle,
            PageRequest pageRequest);

    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
