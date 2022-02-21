package pl.recruitmenttask.warehouse;

import pl.recruitmenttask.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "WHOLESALER", name = "STOCKS")
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STOCK_SEQ")
    @SequenceGenerator(name = "STOCK_SEQ", sequenceName = "WHOLESALER.STOCK_SEQ", allocationSize = 1)
    @Column(updatable = false, nullable = false, name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    private Product product;

    @OneToMany(mappedBy = "stock")
    private List<StockItem> items = new ArrayList<>();

    @Version
    int version;

    protected Stock() {
    }

    public Stock(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public List<StockItem> getItems() {
        return items;
    }

    public void addStockItem(StockItem item) {
        this.items.add(item);
        item.setStock(this);
    }

    public void removeItemFromStock(StockItem item) {
        this.items.remove(item);
        item.setStock(null);
    }
}
