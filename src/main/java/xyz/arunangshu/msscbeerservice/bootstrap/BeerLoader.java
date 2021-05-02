package xyz.arunangshu.msscbeerservice.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.arunangshu.msscbeerservice.domain.Beer;
import xyz.arunangshu.msscbeerservice.repositories.BeerRepository;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .upc(337010000001L)
                    .price(new BigDecimal("12.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .upc(337010000002L)
                    .price(new BigDecimal("11.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build());
        }
    }
}
