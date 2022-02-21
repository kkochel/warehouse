package pl.recruitmenttask.sale;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ResponseBody
@RequestMapping("/api/sales")
public class SaleController {
    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<CollectionModel<SaleDto>> add(@RequestBody SalePost post) {
        if(post.quantity == 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        List<SaleDto> dtos = service.addNew(post);

        if (dtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).build();
        }

        CollectionModel<SaleDto> out = CollectionModel.of(dtos, linkTo(SaleController.class).withSelfRel());

        return ResponseEntity.ok(out);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<CollectionModel<SaleDto>> getSaleHistoryForProduct(@PathVariable Long id) {
        List<SaleDto> history = service.getHistoryForProduct(id);

        CollectionModel<SaleDto> out = CollectionModel.of(history, linkTo(SaleController.class).withSelfRel());

        return ResponseEntity.ok(out);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CollectionModel<SaleDto>> getSaleHistoryForCustomer(@PathVariable Long id) {
        List<SaleDto> history = service.getHistoryForCustomer(id);

        CollectionModel<SaleDto> out = CollectionModel.of(history, linkTo(SaleController.class).withSelfRel());

        return ResponseEntity.ok(out);
    }
}
