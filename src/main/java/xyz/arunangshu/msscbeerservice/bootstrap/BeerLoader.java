package xyz.arunangshu.msscbeerservice.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.arunangshu.msscbeerservice.domain.Beer;
import xyz.arunangshu.msscbeerservice.repositories.BeerRepository;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

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
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("No Hammers On The Bar")
                    .beerStyle("PALE_ALE")
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("11.95"))
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .build());
        }
    }
}
