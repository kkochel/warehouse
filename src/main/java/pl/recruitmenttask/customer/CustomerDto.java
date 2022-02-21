package pl.recruitmenttask.customer;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class CustomerDto extends RepresentationModel<CustomerDto> {
    public String customerName;

    private CustomerDto() {
    }

    public CustomerDto(String customerName) {
        this.customerName = customerName;
    }

    public static CustomerDto fromEntity(Customer entity) {
        return new CustomerDto(entity.getCustomerName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto entity = (CustomerDto) o;
        return Objects.equals(this.customerName, entity.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName);
    }
}
