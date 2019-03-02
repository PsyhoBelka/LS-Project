package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.OrderDAO;
import ua.rozhkov.project.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static volatile OrderDAOImpl instance;
    private static long index = 0;
    private static List<Order> ordersT = new ArrayList<>();

    private OrderDAOImpl() {
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
    public boolean create(Order newEntity) {
        newEntity.setId(index++);
        ordersT.add(newEntity);
        //        return newEntity.getId();
        return true;
    }

    @Override
    public Order get(long idEntity) {
//        System.out.println("Some order");
        for (Order order : ordersT) {
            if (order.getId() == idEntity) return order;
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
//        System.out.println("Order 1");
//        System.out.println("Order 2");
//        System.out.println("Order 3");
        return ordersT;
    }

    @Override
    public boolean update(long idEntity, Order updatedEntity) {
//        System.out.println("Updated successfully!");
        Order tmp = get(idEntity);
        if (tmp != null) {
            updatedEntity.setId(tmp.getId());
            tmp = updatedEntity;
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long idEntity) {
        Order tmp = get(idEntity);
        if (tmp != null) {
            ordersT.remove(tmp);
            return true;
        }
        return false;
    }
}
