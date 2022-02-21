package pl.recruitmenttask.sale;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class SaleDto extends RepresentationModel<SaleDto> {
    public Long customerId;
    public Long productId;
    public LocalDateTime created;
    public UUID uuid;
    public String lotId;

    private SaleDto() {

    }

    public SaleDto(Long customerId, Long productId, LocalDateTime created, UUID uuid, String lotId) {
        this.customerId = customerId;
        this.productId = productId;
        this.created = created;
        this.uuid = uuid;
        this.lotId = lotId;
    }

    public static SaleDto fromEntity(Sale s) {
        return new SaleDto(s.getCustomerId(), s.getProductId(), s.getCreated(), s.getUuid(), s.getLotId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleDto)) return false;
        if (!super.equals(o)) return false;
        SaleDto saleDto = (SaleDto) o;
        return customerId.equals(saleDto.customerId) && productId.equals(saleDto.productId) && created.equals(saleDto.created) && uuid.equals(saleDto.uuid) && lotId.equals(saleDto.lotId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerId, productId, created, uuid, lotId);
    }
}
