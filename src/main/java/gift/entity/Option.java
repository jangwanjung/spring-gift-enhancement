package gift.entity;

import jakarta.persistence.*;

@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Option(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public Option() {}
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }


    public void changeProduct(Product product) {
        this.product = product;
    }


}
