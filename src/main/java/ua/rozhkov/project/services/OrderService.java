package ua.rozhkov.project.services;

import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.models.Order;

import java.math.BigDecimal;

public interface OrderService {
    /**
     * Create new order for specified client
     *
     * @param clientForOrder client for which order will link to
     * @return id of newly created order
     */
    long createOrder(Client clientForOrder);

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
     * Add to order new product
     *
     * @param id             order-id where to add product
     * @param idProductToAdd product-id, which will add to order
     * @return true, if operation successfully
     */
    boolean addToOrder(long id, long idProductToAdd);

    /**
     * Remove from order specified product
     *
     * @param id                product-id, which need to remove from order
     * @param idProductToRemove product-id, which will remove from order
     * @return true, if operation successfully
     */
    boolean removeFromOrder(long id, long idProductToRemove);

    /**
     * Delete specified order
     *
     * @param id order-id to delete
     * @return true, if operation successfully
     */
    boolean deleteOrder(long id);
}
