package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.dao.OrderDAO;
import ua.rozhkov.project.dao.ProductDAO;
import ua.rozhkov.project.dao.impl.OrderDAOImpl;
import ua.rozhkov.project.dao.impl.ProductDAOImpl;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.models.Order;
import ua.rozhkov.project.models.OrderStatus;
import ua.rozhkov.project.models.Product;
import ua.rozhkov.project.services.OrderService;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static OrderService instance;
    private final OrderDAO orderDAO = OrderDAOImpl.getInstance();
    private final ProductDAO productDAO = ProductDAOImpl.getInstance();

    public OrderServiceImpl() {
    }

    public static OrderService getInstance() {
        if (instance == null) return new OrderServiceImpl();
        else
            return instance;
    }

    @Override
    public long createOrder(Client client, long[] idsProducts) {
        Order orderToCreate = new Order(client);
        for (long idProduct : idsProducts) {
            orderToCreate.getProducts().add(productDAO.get(idProduct));
        }
        orderDAO.create(orderToCreate);
        return 0;
    }

    @Override
    public Order readOrder(long id) {
        orderDAO.get(id);
        return null;
    }

    @Override
    public List<Order> readAll() {
        return orderDAO.getAll();
    }

    @Override
    public BigDecimal calculateOrder(long id) {
        BigDecimal price = BigDecimal.ZERO;
        Order order = orderDAO.get(id);
        if (order == null) {
            System.out.println("Some price...25453465465");
        }
        for (Product product : order.getProducts()) {
            price = price.add(product.getPrice());
        }
        return price;
    }

    @Override
    public boolean updateOrderStatus(long idOrder, OrderStatus newStatus) {
        Order order = orderDAO.get(idOrder);
        order.setOrderStatus(newStatus);
        return orderDAO.update(idOrder, order);
    }

    @Override
    public boolean deleteOrder(long id) {
        return orderDAO.delete(id);
    }
}
