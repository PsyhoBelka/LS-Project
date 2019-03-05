package ua.rozhkov.project.services;

import ua.rozhkov.project.models.Order;
import ua.rozhkov.project.models.Product;

import java.util.List;

public interface OrdersProductsService {
    boolean createOrder(Order order, List<Product> products);

    List<Product> getProductsByOrder(long orderId);

    boolean deleteProductFromOrder(long productId);

    boolean deleteOrder();

    List<Order> getOrdersByUser(long userId);
}
