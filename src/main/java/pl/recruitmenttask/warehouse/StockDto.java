package pl.recruitmenttask.warehouse;

import org.springframework.hateoas.RepresentationModel;
import pl.recruitmenttask.product.ProductDto;

import java.util.Objects;

public class StockDto extends RepresentationModel<StockDto> {
    public ProductDto product;
    public int count;

    private StockDto() {
    }

    public StockDto(ProductDto product, int count) {
        this.product = product;
        this.count = count;
    }

    public static StockDto fromEntity(Stock s) {
        return new StockDto(ProductDto.fromEntity(s.getProduct()), s.getItems().size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockDto)) return false;
        if (!super.equals(o)) return false;
        StockDto stockDto = (StockDto) o;
        return count == stockDto.count && product.equals(stockDto.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, count);
    }
}
