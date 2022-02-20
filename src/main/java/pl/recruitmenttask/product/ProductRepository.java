package pl.recruitmenttask.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long>{

    @Query("from Product p where p.name = :name and p.number = :number")
    Optional<Product> getByNameAndCatalogNumber(String name, String number);
}
