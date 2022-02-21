package pl.recruitmenttask.sale;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.recruitmenttask.warehouse.Stock;
import pl.recruitmenttask.warehouse.StockItem;
import pl.recruitmenttask.warehouse.StockRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class SaleService {
    private final SaleRepository saleRepository;
    private final StockRepository stockRepository;

    public SaleService(SaleRepository saleRepository, StockRepository stockRepository) {
        this.saleRepository = saleRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public List<SaleDto> addNew(SalePost post) {
        Stock stock = stockRepository.getStockForProduct(post.productId);

        if (stock.getItems().size() < post.quantity){
            return new ArrayList<>();
        }

        List<StockItem> selectedItems = stock
                .getItems().stream()
                .limit(post.quantity)
                .collect(Collectors.toList());

        for ( StockItem si: selectedItems){
            stock.removeItemFromStock(si);
        }

        List<Sale> entities = selectedItems.stream()
                .map(si -> new Sale(post.productId, post.customerId, si.getUuid(), si.getLotId()))
                .collect(Collectors.toList());

        saleRepository.saveAll(entities);

        return getSaleDtos(entities);
    }

    public List<SaleDto> getHistoryForProduct(Long id) {
        List<Sale> entities = saleRepository.getByProductId(id);

        List<SaleDto> dtos = getSaleDtos(entities);

        return dtos.stream()
                .sorted(Comparator.comparing(o -> o.created))
                .collect(Collectors.toList());
    }

    public List<SaleDto> getHistoryForCustomer(Long id) {
        List<Sale> entities = saleRepository.getByCustomerId(id);

        List<SaleDto> dtos = getSaleDtos(entities);

        return dtos.stream()
                .sorted(Comparator.comparing(o -> o.created))
                .collect(Collectors.toList());
    }

    private List<SaleDto> getSaleDtos(List<Sale> entities) {
        List<SaleDto> dtos = new ArrayList<>();
        for (Sale s : entities) {
            SaleDto dto = SaleDto.fromEntity(s);

            dto.add(linkTo(SaleController.class).slash(s.getId()).withSelfRel());
            dtos.add(dto);
        }
        return dtos;
    }
}
