package bll;

import java.util.ArrayList;
import java.util.List;

import bll.validators.Validator;
import dao.OrderDAO;
import model.Order;

/**
 * The OrderBLL class provides business logic operations for managing orders.
 */
public class OrderBLL {
    private final List<Validator<Order>> validators;

    /**
     * Constructs a new OrderBLL object with no validators.
     */
    public OrderBLL() {
        validators = new ArrayList<>();
    }

    /**
     * Finds an order by its ID.
     *
     * @param id The ID of the order to find.
     * @return The order with the specified ID, or null if not found.
     */
    public Order findOrderById(int id) {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findById(id);
    }

    /**
     * Inserts a new order into the database.
     *
     * @param order The order object to insert.
     * @return The ID of the inserted order.
     */
    public int insertOrder(Order order) {
        // Validate order data
        for (Validator<Order> v : validators){
            v.validate(order);
        }

        OrderDAO orderDAO = new OrderDAO();
        Order insertedOrder = orderDAO.insert(order);
        return insertedOrder.getId();
    }
}
