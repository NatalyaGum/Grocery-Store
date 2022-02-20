package by.edu.webstore.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;


public class Order extends AbstractEntity {
    public enum OrderStatus {REJECTED, PREPARING, DELIVERED, ORDERED}
    public enum PaymentMethod {CASH, CARD}

    private Map<Long, Integer> addedProducts;
    private long orderId;
    private BigDecimal cost;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private PaymentMethod method;
    private long userId;
    private long addressId;


    public Map<Long, Integer> getProducts() {
        return addedProducts;
    }

    public void setProducts(Map<Long, Integer> products) {
        this.addedProducts = products;
    }
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                userId == order.userId &&
                addressId == order.addressId &&
                addedProducts.equals(order.addedProducts) &&
                cost.equals(order.cost) &&
                status == order.status &&
                orderDate.equals(order.orderDate) &&
                method == order.method;
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result=result * first +(addedProducts != null ? addedProducts.hashCode() : 0);
        result = result * first + (int) orderId;
        result = result * first + (int) userId;
        result = result * first + (int) addressId;
        result = result * first + (addedProducts!=null ? addedProducts.hashCode() : 0);
        result = result * first + (cost!=null ? cost.hashCode() : 0);
        result = result * first + (status != null ? status.hashCode() : 0);
        result = result * first + (orderDate != null ? orderDate.hashCode() : 0);
        result = result * first + (method != null ? method.hashCode() : 0);
        return result;
}

        @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order{ ");
        builder.append("orderId=").append(orderId);
        builder.append(", cost=").append(cost);
        builder.append(", status='").append(status);
        builder.append(", orderDate=").append(orderDate);
        builder.append(", method=").append(method);
        builder.append(", userId=").append(userId);
        builder.append(", addressId=").append(addressId);
        builder.append("}");
        return builder.toString();
    }
}
