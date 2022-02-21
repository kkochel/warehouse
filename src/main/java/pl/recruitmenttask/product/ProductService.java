package pl.recruitmenttask.product;

import pl.recruitmenttask.warehouse.Stock;
import pl.recruitmenttask.warehouse.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

class ProductService {
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public ProductService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    List<ProductDto> getAll() {
        List<ProductDto> dtos = new ArrayList<>();
        List<Product> entities = productRepository.findAll();

        for (Product p : entities) {
            ProductDto dto = ProductDto.fromEntity(p);
            dto.add(linkTo(ProductController.class).slash(p.getId()).withSelfRel());
            dtos.add(dto);
        }

        return dtos;
    }

    ProductDto addNew(ProductPost post) {
        Optional<Product> fromDb = productRepository.getByNameAndCatalogNumber(post.name.value, post.number.value);

        if (fromDb.isPresent()) {
            return ProductDto.fromEntity(fromDb.get());
        } else {
            Product newProduct = new Product(post.name.value, post.number.value);
            productRepository.save(newProduct);

            Stock stock = new Stock(newProduct);
            stockRepository.save(stock);

            newProduct.setStock(stock);

            ProductDto dto = ProductDto.fromEntity(newProduct);
            dto.add(linkTo(ProductController.class).slash(newProduct.getId()).withSelfRel());

            return dto;
        }
    }
}
