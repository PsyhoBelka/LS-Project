package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.OrderDAO;
import ua.rozhkov.project.models.Order;
import ua.rozhkov.project.models.Product;
import ua.rozhkov.project.services.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDbDAOImpl implements OrderDAO {
    private static volatile OrderDbDAOImpl instance;
    private DatabaseService databaseService;

    public static OrderDbDAOImpl getInstance() {
        if (instance == null) {
            synchronized (OrderDbDAOImpl.class) {
                if (instance == null)
                    instance = new OrderDbDAOImpl();
            }
        }
        return instance;
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public boolean create(Order newEntity) {
        if (newEntity != null) {
            try (Connection connection = databaseService.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO PUBLIC.ORDERS (CLIENT_ID, STATUS) " +
                                "VALUES (?, ?)");
                preparedStatement.setLong(1, newEntity.getClient().getId());
                preparedStatement.setString(2, newEntity.getOrderStatus().toString());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                preparedStatement.close();
                for (Product product : newEntity.getProducts()) {
                    PreparedStatement preparedStatement1 = connection.prepareStatement(
                            "insert into PUBLIC.ORDERS_PRODUCTS(ORDER_ID, PRODUCT_ID) " +
                                    "values (?,?)");
                    preparedStatement1.setLong(1, resultSet.getInt("id"));
                    preparedStatement1.setLong(2, product.getId());
                    preparedStatement1.execute();
                    preparedStatement1.close();
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Order get(long idEntity) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public boolean update(long idEntity, Order updatedEntity) {
        return false;
    }

    @Override
    public boolean delete(long idEntity) {
        return false;
    }
}
