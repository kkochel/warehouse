package pl.recruitmenttask.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    public List<CustomerDto> getAll() {
        List<CustomerDto> dtos = new ArrayList<>();
        List<Customer> entities = repository.findAll();

        for (Customer c : entities) {
            CustomerDto dto = CustomerDto.fromEntity(c);
            dto.add(linkTo(CustomerController.class).slash(c.getId()).withSelfRel());
            dtos.add(dto);
        }

        return dtos;
    }

    public CustomerDto addNew(CustomerPost post) {
        Optional<Customer> fromDb = repository.getByName(post.name);

        if(fromDb.isPresent()){
            return CustomerDto.fromEntity(fromDb.get());
        } else {
            Customer newRow = new Customer(post.name);
            repository.save(newRow);

            CustomerDto dto = CustomerDto.fromEntity(newRow);
            dto.add(linkTo(CustomerController.class).slash(newRow.getId()).withSelfRel());

            return dto;
        }
    }
}
