package pl.recruitmenttask.warehouse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WarehouseConfiguration {

    @Bean
    WarehouseService warehouseService(StockRepository stockRepository, StockItemsRepository stockItemsRepository) {
        return new WarehouseService(stockRepository, stockItemsRepository);
    }

    @Bean
    WarehouseController warehouseController(WarehouseService warehouseService) {
        return new WarehouseController(warehouseService);
    }
}
