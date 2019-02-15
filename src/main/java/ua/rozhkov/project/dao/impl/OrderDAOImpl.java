package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.OrderDAO;
import ua.rozhkov.project.models.Order;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static volatile OrderDAOImpl instance;

    public OrderDAOImpl() {
    }

    public static OrderDAOImpl getInstance() {
        if (instance == null)
            synchronized (OrderDAOImpl.class) {
                if (instance == null)
                    instance = new OrderDAOImpl();
            }
        return instance;
    }

    @Override
    public long create(Order newEntity) {
        return 0;
    }

    @Override
    public Order get(long idEntity) {
        System.out.println("Some order");
        return null;
    }

    @Override
    public List<Order> getAll() {
        System.out.println("Order 1");
        System.out.println("Order 2");
        System.out.println("Order 3");
        return null;
    }

    @Override
    public boolean update(long idEntity, Order updatedEntity) {
        System.out.println("Updated successfully!");
        return false;
    }

    @Override
    public boolean delete(long idEntity) {
        return false;
    }
}
