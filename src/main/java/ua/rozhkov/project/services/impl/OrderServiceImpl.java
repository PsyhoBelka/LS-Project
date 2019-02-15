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
    private static volatile OrderService instance;
    private final OrderDAO orderDAO = OrderDAOImpl.getInstance();
    private final ProductDAO productDAO = ProductDAOImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderService getInstance() {
        if (instance == null)
            synchronized (OrderServiceImpl.class) {
                if (instance == null)
                    instance = new OrderServiceImpl();
            }
        return instance;
    }

    @Override
    public long createOrder(Client client, long[] idsProducts) {
        Order orderToCreate = new Order(client);
        for (long idProduct : idsProducts) {
            orderToCreate.getProducts().add(productDAO.get(idProduct));
        }
        return orderDAO.create(orderToCreate);
    }

    @Override
    public Order readOrder(long id) {
        return orderDAO.get(id);
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
        } else
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
