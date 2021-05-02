package xyz.arunangshu.msscbeerservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.arunangshu.msscbeerservice.domain.Beer;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
