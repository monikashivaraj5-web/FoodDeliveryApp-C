package edu.classproject.order;

import java.util.*;

public class MockOrderService implements OrderService {

    private Map<String, Order> orders = new HashMap<>();

    public void addOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    @Override
    public Order getOrderById(String orderId) {
        return orders.get(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public void updateOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }
}