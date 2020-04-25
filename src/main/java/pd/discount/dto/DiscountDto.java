package pd.discount.dto;

import pd.discount.Discount;
import pd.product.dto.ProductDto;

import java.math.BigDecimal;
import java.time.Instant;

public class DiscountDto {
    private int id;
    private ProductDto product;
    private String description;
    private Double percent;
    private Instant activateFrom;
    private Instant activateBy;

    public DiscountDto(Discount discount) {
        this.id = discount.getId();
        this.product = new ProductDto(discount.getProduct());
        this.description = discount.getDescription();
        this.percent = discount.getPercent();
        this.activateFrom = discount.getActivateFrom();
        this.activateBy = discount.getActivateBy();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
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
