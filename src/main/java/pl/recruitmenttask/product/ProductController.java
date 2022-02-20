package pl.recruitmenttask.product;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ResponseBody
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService productService) {
        this.service = productService;
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<ProductDto>> all() {
        List<ProductDto> all = service.getAll();

        for (ProductDto d : all) {
            d.add(linkTo(ProductController.class).slash(d.id).withSelfRel());
        }

        CollectionModel<ProductDto> out = CollectionModel.of(all, linkTo(ProductController.class).withSelfRel());
        return ResponseEntity.ok(out);
    }

    @PostMapping()
    public ProductDto add(@RequestBody ProductPost post) {
        ProductDto dto = service.addNew(post);

        return dto.add(linkTo(ProductController.class).slash(dto.id).withSelfRel());
    }
}
