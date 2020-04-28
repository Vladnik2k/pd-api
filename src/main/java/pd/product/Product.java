package pd.product;

import pd.category.Category;
import pd.product.dto.ProductDto;

import javax.persistence.*;

@Entity
@Table(name = "product")
@SqlResultSetMapping(
        name="ProductJoinDiscount",
        classes = {
                @ConstructorResult(
                        targetClass = ProductDto.class,
                        columns = {
                                @ColumnResult(name = "id"),
                                @ColumnResult(name = "name"),
                                @ColumnResult(name = "description"),
                                @ColumnResult(name = "categoryId"),
                                @ColumnResult(name = "imageUrl"),
                                @ColumnResult(name = "isDiscount"),
                                @ColumnResult(name = "price"),
                                @ColumnResult(name = "discountPercent"),
                                @ColumnResult(name = "discountPrice")
                        }
                )
        })
@NamedNativeQuery(name = "findAllByFilter", query =
        "SELECT product.id AS id, " +
                "       product.name AS name, " +
                "       product.description AS description, " +
                "       product.category_id AS categoryId, " +
                "       product.image_url AS imageUrl,\n" +
                "       product.price AS price,\n" +
                "       IF(discount.percent IS NULL, NULL, discount.percent) AS discountPercent\n" +
                "       IF(discount.percent IS NULL, price, price - price / 100 * discount.percent) AS discountPrice,\n" +
                "    FROM product\n" +
                "        LEFT JOIN discount ON discount.product_id = product.id\n" +
                "                                  AND discount.activate_by >= CURRENT_TIMESTAMP\n" +
                "                                  AND discount.activate_from <= CURRENT_TIMESTAMP ",
        resultSetMapping = "ProductJoinDiscount")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "image_url")
    private String imageUrl;

    public Product() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
