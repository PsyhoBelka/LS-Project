package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.models.Order;
import ua.rozhkov.project.models.Product;
import ua.rozhkov.project.services.DatabaseService;
import ua.rozhkov.project.services.OrdersProductsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrdersProductsServiceImpl implements OrdersProductsService {
    private static volatile OrdersProductsServiceImpl instance;
    DatabaseService databaseService;

    public static OrdersProductsServiceImpl getInstance() {
        if (instance == null) {
            synchronized (OrdersProductsServiceImpl.class) {
                if (instance == null)
                    instance = new OrdersProductsServiceImpl();
            }
        }
        return instance;
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public boolean createOrder(Order order, List<Product> products) {
        if ((order != null) && (products.size() > 0)) {

        }
        return false;
    }

    @Override
    public List<Product> getProductsByOrder(long orderId) {
        if (orderId > 0) {
            try (Connection connection = databaseService.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * FROM PRODUCTS"
                                                                                 );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean deleteProductFromOrder(long productId) {
        return false;
    }

    @Override
    public boolean deleteOrder() {
        return false;
    }

    @Override
    public List<Order> getOrdersByUser(long userId) {
        return null;
    }
}
