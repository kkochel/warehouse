package pl.recruitmenttask.product;

import pl.recruitmenttask.commons.CatalogNumber;
import pl.recruitmenttask.commons.Name;

import java.util.Objects;

class ProductPost {
    public Name name;
    public CatalogNumber number;

    private ProductPost() {
    }

    public ProductPost(Name name, CatalogNumber number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductPost)) return false;
        ProductPost that = (ProductPost) o;
        return name.equals(that.name) && number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }
}
