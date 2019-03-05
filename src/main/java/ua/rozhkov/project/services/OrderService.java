package ua.rozhkov.project.services;

import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.models.Order;
import ua.rozhkov.project.models.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    /**
     * Create new order for specified client
     *
     * @param currClient  client, who ordering
     * @param idsProducts products-id to create order
     * @return id of newly created order
     */
    boolean createOrder(Client currClient, long[] idsProducts);

    /**
     * Read order by id
     *
     * @param id order-id for read
     * @return instance of read order
     */
    Order getOrder(long id);

    /**
     * Read all available orders
     *
     * @return list of all orders
     */
    List<Order> getAllOrders();

    /**
     * Calculate order's total amount
     *
     * @param id order-id to calculate
     * @return total amount of order
     */
    BigDecimal calculateOrder(long id);

    /**
     * Update order status
     *
     * @param idOrder   order-id to update status
     * @param newStatus new order status
     * @return true, if operation successfully
     */
    boolean updateOrderStatus(long idOrder, OrderStatus newStatus);

    /**
     * Delete specified order
     *
     * @param id order-id to delete
     * @return true, if operation successfully
     */
    boolean deleteOrder(long id);
}
