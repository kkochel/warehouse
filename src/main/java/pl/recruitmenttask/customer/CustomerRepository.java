package pl.recruitmenttask.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("from Customer c where c.customerName = :name")
    Optional<Customer> getByName(String name);
}
