import edu.classproject.order.*;
import edu.classproject.restaurantops.*;

public class Main {
    public static void main(String[] args) {

        OrderService orderService = new MockOrderService();

        Order o1 = new Order("O1", "R1", OrderStatus.PLACED);
        Order o2 = new Order("O2", "R1", OrderStatus.PLACED);

        ((MockOrderService) orderService).addOrder(o1);
        ((MockOrderService) orderService).addOrder(o2);

        RestaurantOrderBoardService board =
                new RestaurantOrderBoardServiceImpl(orderService);

        System.out.println("Active Orders:");
        board.getActiveOrders("R1")
                .forEach(o -> System.out.println(o.getOrderId() + " - " + o.getStatus()));

        // Valid flow
        board.markPreparing("O1");
        board.markReadyForPickup("O1");

        // Invalid test
        try {
            board.markReadyForPickup("O2"); // should fail
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }

        System.out.println("\nFinal State:");
        orderService.getAllOrders()
                .forEach(o -> System.out.println(o.getOrderId() + " - " + o.getStatus()));
    }
}