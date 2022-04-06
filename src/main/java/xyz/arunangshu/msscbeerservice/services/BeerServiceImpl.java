package xyz.arunangshu.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xyz.arunangshu.msscbeerservice.domain.Beer;
import xyz.arunangshu.msscbeerservice.exceptions.NotFoundException;
import xyz.arunangshu.msscbeerservice.repositories.BeerRepository;
import xyz.arunangshu.msscbeerservice.web.mappers.BeerMapper;
import xyz.arunangshu.msscbeerservice.web.model.BeerDto;
import xyz.arunangshu.msscbeerservice.web.model.BeerPagedList;
import xyz.arunangshu.msscbeerservice.web.model.BeerStyleEnum;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    public BeerPagedList listBeers(
            String beerName,
            BeerStyleEnum beerStyle,
            PageRequest pageRequest, Boolean showInventoryOnHand) {

        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !Objects.isNull(beerStyle)) {
            // Search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle.name(), pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && Objects.isNull(beerStyle)) {
            // Search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !Objects.isNull(beerStyle)) {
            // Search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle.name(), pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        BeerPagedList beerPagedList;

        if (showInventoryOnHand) {
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        } else {
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }

        return beerPagedList;
    }

    @Override
    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
        if (showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(
                    beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        } else {
            return beerMapper.beerToBeerDto(
                    beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(
                beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    @Cacheable(cacheNames = "beerUpcCache", key = "#upc")
    public BeerDto getByUpc(String upc) {
        return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new));
    }
}
