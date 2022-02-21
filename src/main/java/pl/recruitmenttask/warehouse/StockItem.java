package pl.recruitmenttask.warehouse;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(schema = "WHOLESALER", name = "STOCK_ITEMS")
@Entity
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STOCK_ITEM_SEQ")
    @SequenceGenerator(name = "STOCK_ITEM_SEQ", sequenceName = "WHOLESALER.STOCK_ITEM_SEQ", allocationSize = 1)
    @Column(updatable = false, nullable = false, name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stock;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    private UUID uuid;

    private  String lotId;

    protected StockItem() {
    }

    public StockItem(String lotId) {
        this.lotId = lotId;
        uuid = UUID.randomUUID();
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public Stock getStock() {
        return stock;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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
}
