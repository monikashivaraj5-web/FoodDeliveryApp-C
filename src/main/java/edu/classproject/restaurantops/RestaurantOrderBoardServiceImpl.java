package edu.classproject.restaurantops;

import edu.classproject.order.*;
import edu.classproject.restaurantops.exception.*;

import java.util.List;
import java.util.stream.Collectors;

import edu.classproject.restaurantops.exception.InvalidOrderStateException;
import edu.classproject.restaurantops.exception.OrderNotFoundException;

public class RestaurantOrderBoardServiceImpl implements RestaurantOrderBoardService {

    private final OrderService orderService;

    public RestaurantOrderBoardServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public List<Order> getActiveOrders(String restaurantId) {
        return orderService.getAllOrders().stream()
                .filter(order -> order.getRestaurantId().equals(restaurantId))
                .filter(order ->
                        order.getStatus() == OrderStatus.PLACED ||
                        order.getStatus() == OrderStatus.PREPARING)
                .collect(Collectors.toList());
    }

    @Override
    public void markPreparing(String orderId) {
        Order order = orderService.getOrderById(orderId);

        if (order == null) {
            throw new OrderNotFoundException("Order not found: " + orderId);
        }

        if (order.getStatus() != OrderStatus.PLACED) {
            throw new InvalidOrderStateException(
                    "Invalid transition: Only PLACED → PREPARING allowed"
            );
        }

        order.setStatus(OrderStatus.PREPARING);
        orderService.updateOrder(order);
    }

    @Override
    public void markReadyForPickup(String orderId) {
        Order order = orderService.getOrderById(orderId);

        if (order == null) {
            throw new OrderNotFoundException("Order not found: " + orderId);
        }

        if (order.getStatus() != OrderStatus.PREPARING) {
            throw new InvalidOrderStateException(
                    "Invalid transition: Only PREPARING → READY_FOR_PICKUP allowed"
            );
        }

        order.setStatus(OrderStatus.READY_FOR_PICKUP);
        orderService.updateOrder(order);
    }
}