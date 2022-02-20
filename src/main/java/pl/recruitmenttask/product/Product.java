package pl.recruitmenttask.product;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(schema = "WAREHOUSE", name = "PRODUCTS")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTS_SEQ")
    @SequenceGenerator(name = "PRODUCTS_SEQ", sequenceName = "WAREHOUSE.PRODUCTS_SEQ", allocationSize = 1)
    @Column(updatable = false, nullable = false, name = "id")
    private Long id;

    @Length(message = "Invalid length", min = 3, max = 250)
    @NotNull
    @Column(nullable = false)
    private String name;

    @Length(message = "Invalid length", min = 3, max = 250)
    @NotNull
    @Column(nullable = false, unique = true)
    private String number;


    @Version
    int version;

    public void setId(Long id) {
        this.id = id;
    }

    protected Product() {
    }

    public Product(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
