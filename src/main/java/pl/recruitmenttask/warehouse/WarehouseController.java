package pl.recruitmenttask.warehouse;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ResponseBody
@RequestMapping("/api/warehouse")
public class WarehouseController {
    private final WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<CollectionModel<StockDto>> getWholeStock() {
        List<StockDto> all = service.getStock();

        CollectionModel<StockDto> out = CollectionModel.of(all, linkTo(WarehouseController.class).withSelfRel());

        return ResponseEntity.ok(out);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<StockDto> addToStock(@PathVariable Long id, @RequestBody List<StockItemDto> items) {
       StockDto dto = service.addToStock(id, items);

       return ResponseEntity.ok(dto);
    }
}
