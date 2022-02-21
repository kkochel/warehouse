package pl.recruitmenttask.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemsRepository extends JpaRepository<StockItem, Long> {
}
