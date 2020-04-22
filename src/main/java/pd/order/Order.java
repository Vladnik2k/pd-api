package pd.order;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "is_need_delivery")
    private boolean isNeedDelivery;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "schedule")
    private Instant schedule;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_surname")
    private String customerSurname;

    public Order() {}

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

    public boolean isNeedDelivery() {
        return isNeedDelivery;
    }

    public void setNeedDelivery(boolean needDelivery) {
        isNeedDelivery = needDelivery;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getSchedule() {
        return schedule;
    }

    public void setSchedule(Instant schedule) {
        this.schedule = schedule;
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
}
