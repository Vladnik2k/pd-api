package pd.order.dto;

import pd.order.Order;
import pd.product.Product;

import java.util.Map;

public class OrderDto {
    private int id;
    private String email;
    private String status;
    private String deliveryAddress;
    private String customerName;
    private String customerSurname;
    private double price;

    private String products;

    public OrderDto(Order order, Map<Product, Integer> products) {
        this.id = order.getId();
        this.email = order.getEmail();
        this.status = order.getStatus().getName();
        this.deliveryAddress = order.getDeliveryAddress();
        this.customerName = order.getCustomerName();
        this.customerSurname = order.getCustomerSurname();
        this.price = order.getPrice();

        this.products = convertMapToString(products);
    }

    private String convertMapToString(Map<Product, Integer> products) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Product key : products.keySet()) {
            stringBuilder
                    .append("[")
                    .append(key.getId())
                    .append(",")
                    .append(products.get(key))
                    .append("]")
                    .append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("]");
        return stringBuilder.toString();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
}
