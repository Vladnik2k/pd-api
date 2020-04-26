package pd.discount;

import pd.product.Product;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "description")
    private String description;

    @Column(name = "percent")
    private Double percent;

    @Column(name = "activate_from")
    private Instant activateFrom;

    @Column(name = "activate_by")
    private Instant activateBy;

    public Discount() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Instant getActivateFrom() {
        return activateFrom;
    }

    public void setActivateFrom(Instant activateFrom) {
        this.activateFrom = activateFrom;
    }

    public Instant getActivateBy() {
        return activateBy;
    }

    public void setActivateBy(Instant activateBy) {
        this.activateBy = activateBy;
    }
}
