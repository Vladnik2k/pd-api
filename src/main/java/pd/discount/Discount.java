package pd.discount;

import pd.product.Product;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private BigDecimal percent;

    @Column(name = "activate_from")
    private Instant activateFrom;

    @Column(name = "activate_by")
    private Instant activateBy;

    public Discount() {}

    public Discount(Product product, String description, BigDecimal percent, Instant activateFrom, Instant activateBy) {
        this.product = product;
        this.description = description;
        this.percent = percent;
        this.activateFrom = activateFrom;
        this.activateBy = activateBy;
    }

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

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
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
