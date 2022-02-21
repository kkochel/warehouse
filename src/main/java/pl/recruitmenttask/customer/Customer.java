package pl.recruitmenttask.customer;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Table(schema = "WHOLESALER", name = "CUSTOMERS")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMERS_SEQ")
    @SequenceGenerator(name = "CUSTOMERS_SEQ", sequenceName = "WHOLESALER.CUSTOMERS_SEQ", allocationSize = 1)
    @Column(updatable = false, nullable = false, name = "id")
    private Long id;

    @Length(message = "Invalid length", min = 10, max = 250)
    @Column(name = "customer_name" , unique = true)
    private String customerName;

    @Version
    private int version;

    protected Customer() {
    }

    public Long getId() {
        return id;
    }

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
