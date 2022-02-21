package pl.recruitmenttask.sale;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(schema = "WHOLESALER", name = "SALES")
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALE_SEQ")
    @SequenceGenerator(name = "SALE_SEQ", sequenceName = "WHOLESALER.SALE_SEQ", allocationSize = 1)
    @Column(updatable = false, nullable = false, name = "id")
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "lot_id", nullable = false)
    private  String lotId;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    protected Sale() {
    }

    public Sale(Long productId, Long customerId, UUID uuid, String lotId) {
        this.productId = productId;
        this.customerId = customerId;
        this.uuid = uuid;
        this.lotId = lotId;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
