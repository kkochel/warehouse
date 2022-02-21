package pl.recruitmenttask.warehouse;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.recruitmenttask.product.ProductController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class WarehouseService {
    private final StockRepository stockRepository;
    private final StockItemsRepository itemsRepository;

    public WarehouseService(StockRepository repository, StockItemsRepository itemsRepository) {
        this.stockRepository = repository;
        this.itemsRepository = itemsRepository;
    }

    public List<StockDto> getStock() {
        List<StockDto> dtos = new ArrayList<>();
        List<Stock> entities = stockRepository.findAll();

        for (Stock s : entities) {
            StockDto dto = StockDto.fromEntity(s);

            dto.product.add(linkTo(ProductController.class).slash(s.getProduct().getId()).withSelfRel());
            dto.add(linkTo(WarehouseController.class).slash(s.getId()).withSelfRel());
            dtos.add(dto);
        }


        return dtos;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public StockDto addToStock(Long id, List<StockItemDto> dtos) {
        Stock stock = stockRepository.getById(id);

        List<StockItem> items = new ArrayList<>();
        for (StockItemDto dto : dtos) {
            StockItem item = new StockItem(dto.lotId);
            items.add(item);
        }

        itemsRepository.saveAll(items);

        items.forEach(stock::addStockItem);


        return null;
    }
}
