package pl.recruitmenttask.sale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.recruitmenttask.warehouse.StockRepository;

@Configuration
public class SaleConfiguration {

    @Bean
    public SaleService saleService (SaleRepository saleRepository, StockRepository stockRepository){
        return new SaleService(saleRepository, stockRepository);
    }

    @Bean
    public SaleController saleController(SaleService saleService) {
        return new SaleController(saleService);
    }
}
