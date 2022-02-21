package pl.recruitmenttask.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository <Stock, Long> {
}
