package pl.recruitmenttask.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.recruitmenttask.warehouse.StockRepository;

@Configuration
class ProductConfiguration {


    @Bean
    ProductService productService(ProductRepository productRepository, StockRepository stockRepository) {
        return new ProductService(productRepository,stockRepository);
    }

    @Bean
    ProductController productController(ProductService productService) {
        return new ProductController(productService);
    }
}
