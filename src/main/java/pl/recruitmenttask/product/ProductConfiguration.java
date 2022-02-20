package pl.recruitmenttask.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductConfiguration {


    @Bean
    ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    @Bean
    ProductController productController(ProductService productService) {
        return new ProductController(productService);
    }
}
