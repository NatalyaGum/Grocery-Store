package by.edu.webstore.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;



public class Order extends AbstractEntity {
    public enum OrderStatus {
        REJECTED("rejected"),
        PREPARING("preparing"),
        DELIVERED("delivered"),
        ORDERED("ordered");
        private String status;
        OrderStatus (String status){this.status=status;}
        public String toString() {
            return status;
        }


    }
    public enum PaymentMethod {
        CASH("cash"),
        CARD("card");
        private String method;
        PaymentMethod (String method){this.method=method;}
        public String toString() {
            return method;
        }
    }

    private Map<Product, Integer> addedProducts;
    private long orderId;
    private BigDecimal cost;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private PaymentMethod method;
    private User user;
    private Address address;

    public Order() {
    }
    public Order(Map<Product, Integer> addedProducts, BigDecimal cost, OrderStatus status, LocalDateTime orderDate, PaymentMethod method, Address address) {
        this.addedProducts = addedProducts;
        this.cost = cost;
        this.status = status;
        this.orderDate = orderDate;
        this.method = method;
        this.address = address;
    }

    public Order(Map<Product, Integer> addedProducts, BigDecimal cost, OrderStatus status, LocalDateTime orderDate, PaymentMethod method, Address address, User user) {
        this.addedProducts = addedProducts;
        this.cost = cost;
        this.status = status;
        this.orderDate = orderDate;
        this.method = method;
        this.address = address;
        this.user=user;
    }

    public Map<Product, Integer> getProducts() {
        return addedProducts;
    }

    public void setProducts(Map<Product, Integer> products) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                user.equals(order.user) &&
                address.equals(order.address) &&
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
        result = result * first + (user!=null ? user.hashCode() : 0);
        result = result * first +  address.hashCode();
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
        builder.append(", userId=").append(user);
        builder.append(", addressId=").append(address);
        builder.append("}");
        return builder.toString();
    }
}
