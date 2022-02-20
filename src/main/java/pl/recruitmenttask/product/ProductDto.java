package pl.recruitmenttask.product;

import org.springframework.hateoas.RepresentationModel;
import pl.recruitmenttask.commons.CatalogNumber;
import pl.recruitmenttask.commons.Name;

import java.util.Objects;

class ProductDto extends RepresentationModel<ProductDto> {
    public Long id;
    public Name name;
    public CatalogNumber number;

    public ProductDto(Long id, Name name, CatalogNumber number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public static ProductDto fromEntity(Product product){
        return new ProductDto(product.getId(), new Name(product.getName()), new CatalogNumber(product.getNumber()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDto)) return false;
        if (!super.equals(o)) return false;
        ProductDto that = (ProductDto) o;
        return id.equals(that.id) && name.equals(that.name) && number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, number);
    }
}
