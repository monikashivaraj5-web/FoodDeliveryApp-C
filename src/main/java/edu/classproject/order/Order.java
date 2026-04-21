package edu.classproject.order;

public class Order {
    private String orderId;
    private String restaurantId;
    private OrderStatus status;

    public Order(String orderId, String restaurantId, OrderStatus status) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}