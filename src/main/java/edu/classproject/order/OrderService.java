package edu.classproject.order;

import java.util.List;

public interface OrderService {
    Order getOrderById(String orderId);
    List<Order> getAllOrders();
    void updateOrder(Order order);
}