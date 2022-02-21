package pl.recruitmenttask.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("from Stock s where s.product.id = :productId")
    Stock getStockForProduct(Long productId);

}
