package com.github.dotslash21.msscbeerservice.web.mappers;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.dotslash21.msscbeerservice.domain.Beer;
import com.github.dotslash21.msscbeerservice.services.inventory.BeerInventoryService;
import com.github.dotslash21.msscbeerservice.web.model.BeerDto;

public abstract class BeerMapperDecorator implements BeerMapper {

    @Setter(onMethod_ = {@Autowired})
    private BeerInventoryService beerInventoryService;

    @Setter(onMethod_ = {@Autowired})
    private BeerMapper mapper;

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        return mapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto beerDto = mapper.beerToBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return beerDto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return mapper.beerDtoToBeer(beerDto);
    }
}
