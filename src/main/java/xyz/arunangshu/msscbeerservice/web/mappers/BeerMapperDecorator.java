package xyz.arunangshu.msscbeerservice.web.mappers;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.arunangshu.msscbeerservice.domain.Beer;
import xyz.arunangshu.msscbeerservice.services.inventory.BeerInventoryService;
import xyz.arunangshu.msscbeerservice.web.model.BeerDto;

public abstract class BeerMapperDecorator implements BeerMapper {

    @Setter(onMethod_ = {@Autowired})
    private BeerInventoryService beerInventoryService;

    @Setter(onMethod_ = {@Autowired})
    private BeerMapper mapper;

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        BeerDto beerDto = mapper.beerToBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return beerDto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return mapper.beerDtoToBeer(beerDto);
    }
}
