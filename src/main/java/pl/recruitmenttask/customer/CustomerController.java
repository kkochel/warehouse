package pl.recruitmenttask.customer;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ResponseBody
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<CustomerDto>> all() {
        List<CustomerDto> all = service.getAll();

        CollectionModel<CustomerDto> out = CollectionModel.of(all, linkTo(CustomerController.class).withSelfRel());

        return ResponseEntity.ok(out);
    }

    @PostMapping()
    public ResponseEntity<CustomerDto> add(@RequestBody CustomerPost post) {
        return ResponseEntity.ok(service.addNew(post));
    }
}
