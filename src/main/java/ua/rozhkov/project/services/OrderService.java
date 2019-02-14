package ua.rozhkov.project.services;

import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.models.Order;

import java.math.BigDecimal;

public interface OrderService {
    /**
     * Create new order for specified client
     *
     * @param client      client, who ordering
     * @param idsProducts products-id to create order
     * @return id of newly created order
     */
    long createOrder(Client client, long[] idsProducts);

    /**
     * Read order by id
     *
     * @param id order-id for read
     * @return instance of read order
     */
    Order readOrder(long id);

    /**
     * Calculate order's total amount
     *
     * @param id order-id to calculate
     * @return total amount of order
     */
    BigDecimal calculateOrder(long id);

    /**
     * Update order status
     * @param idOrder order-id to update status
     * @return true, if operation successfully
     */
    boolean updateOrderStatus(long idOrder);

    /**
     * Delete specified order
     *
     * @param id order-id to delete
     * @return true, if operation successfully
     */
    boolean deleteOrder(long id);
}
