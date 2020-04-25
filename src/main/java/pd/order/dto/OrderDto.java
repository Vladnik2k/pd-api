package pd.order.dto;

import pd.order.Order;
import pd.product.Product;
import pd.product.dto.ProductDto;

import java.util.HashMap;
import java.util.Map;

public class OrderDto {
    private int id;
    private String email;
    private Integer status;
    private String deliveryAddress;
    private String customerName;
    private String customerSurname;
    private double price;

    private Map<ProductDto, Integer> products = new HashMap<>();

    public OrderDto(Order order, Map<Product, Integer> products) {
        this.id = order.getId();
        this.email = order.getEmail();
        this.status = order.getStatus();
        this.deliveryAddress = order.getDeliveryAddress();
        this.customerName = order.getCustomerName();
        this.customerSurname = order.getCustomerSurname();
        this.price = order.getPrice();

        products.forEach((product, quantity) -> this.products.put(new ProductDto(product), quantity));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<ProductDto, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductDto, Integer> products) {
        this.products = products;
    }
}
