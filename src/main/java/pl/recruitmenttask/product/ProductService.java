package pl.recruitmenttask.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    List<ProductDto> getAll() {
        List<Product> all = repository.findAll();
        return all.stream().map(ProductDto::fromEntity).collect(Collectors.toList());
    }

    ProductDto addNew(ProductPost post) {
        Optional<Product> fromDb = repository.getByNameAndCatalogNumber(post.name.value, post.number.value);

        if (fromDb.isPresent()) {
            return ProductDto.fromEntity(fromDb.get());
        } else {
            Product newProduct = new Product(post.name.value, post.number.value);
            repository.save(newProduct);
            return ProductDto.fromEntity(newProduct);
        }
    }
}
