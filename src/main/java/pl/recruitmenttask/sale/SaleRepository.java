package pl.recruitmenttask.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("from Sale s where s.productId = :id")
    List<Sale> getByProductId(Long id);

    @Query("from Sale s where s.customerId = :id")
    List<Sale> getByCustomerId(Long id);
}
