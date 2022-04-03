package xyz.arunangshu.msscbeerservice.services.inventory.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerInventoryDto extends BaseItem {

    @Builder
    public BeerInventoryDto(
            UUID id,
            Integer version,
            OffsetDateTime createdDate,
            OffsetDateTime lastModifiedDate,
            UUID beerId,
            String upc,
            Integer quantityOnHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.upc = upc;
        this.quantityOnHand = quantityOnHand;
    }

    private UUID beerId;
    private String upc;
    private Integer quantityOnHand;
}
