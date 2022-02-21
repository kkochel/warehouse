package pl.recruitmenttask.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {

    @Bean
    CustomerService customerService(CustomerRepository repository) {
        return new CustomerService(repository);
    }

    @Bean
    CustomerController customerController(CustomerService service) {
        return new CustomerController(service);
    }
}
