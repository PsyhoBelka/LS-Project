package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.dao.OrderDAO;
import ua.rozhkov.project.dao.ProductDAO;
import ua.rozhkov.project.dao.impl.OrderDAOImpl;
import ua.rozhkov.project.dao.impl.ProductDAOImpl;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.models.Order;
import ua.rozhkov.project.services.OrderService;

import java.math.BigDecimal;

public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl instance;
    private final OrderDAO orderDAO = OrderDAOImpl.getInstance();
    private final ProductDAO productDAO = ProductDAOImpl.getInstance();

    public OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
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
        return null;
    }

    @Override
    public BigDecimal calculateOrder(long id) {
        return null;
    }

    @Override
    public boolean updateOrderStatus(long idOrder) {
        return false;
    }

    @Override
    public boolean deleteOrder(long id) {
        return false;
    }
}
