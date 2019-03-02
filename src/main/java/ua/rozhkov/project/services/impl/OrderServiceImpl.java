package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.dao.OrderDAO;
import ua.rozhkov.project.dao.ProductDAO;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.models.Order;
import ua.rozhkov.project.models.OrderStatus;
import ua.rozhkov.project.models.Product;
import ua.rozhkov.project.services.OrderService;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static volatile OrderService instance;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    private OrderServiceImpl() {
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
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
        if (client != null) {
            Order orderToCreate = new Order(client);
            for (long idProduct : idsProducts) {
                orderToCreate.getProducts().add(productDAO.get(idProduct));
            }
            //TODO fix here
            //            return orderDAO.create(orderToCreate);
        }
        return -1;
    }

    @Override
    public Order getOrder(long idOrder) {
        if (idOrder >= 0)
            return orderDAO.get(idOrder);
        else
            return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    @Override
    public BigDecimal calculateOrder(long idOrder) {
        if (idOrder >= 0) {
            BigDecimal price = BigDecimal.ZERO;
            Order order = orderDAO.get(idOrder);
            for (Product product : order.getProducts()) {
                price = price.add(product.getPrice());
            }
            return price;
        }
        return BigDecimal.valueOf(-1);
    }

    @Override
    public boolean updateOrderStatus(long idOrder, OrderStatus newStatus) {
        if (idOrder >= 0) {
            Order order = orderDAO.get(idOrder);
            order.setOrderStatus(newStatus);
            return orderDAO.update(idOrder, order);
        }
        return false;
    }

    @Override
    public boolean deleteOrder(long idOrder) {
        if (idOrder >= 0) {
            return orderDAO.delete(idOrder);
        }
        return false;
    }
}
