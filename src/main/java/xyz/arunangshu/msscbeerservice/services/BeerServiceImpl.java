package xyz.arunangshu.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.arunangshu.msscbeerservice.domain.Beer;
import xyz.arunangshu.msscbeerservice.exceptions.NotFoundException;
import xyz.arunangshu.msscbeerservice.repositories.BeerRepository;
import xyz.arunangshu.msscbeerservice.web.mappers.BeerMapper;
import xyz.arunangshu.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
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
}
