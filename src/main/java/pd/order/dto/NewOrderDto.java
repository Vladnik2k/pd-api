package pd.order.dto;

import java.time.Instant;

public class NewOrderDto {
    private String email;
    private boolean isNeedDelivery;
    private Instant schedule;
    private String customerName;
    private String customerSurname;

    public NewOrderDto(String email, boolean isNeedDelivery, Instant schedule, String customerName, String customerSurname) {
        this.email = email;
        this.isNeedDelivery = isNeedDelivery;
        this.schedule = schedule;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
    }

    public NewOrderDto() {
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
