package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.OrderDAO;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.models.Order;
import ua.rozhkov.project.models.OrderStatus;
import ua.rozhkov.project.models.Product;
import ua.rozhkov.project.services.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        Order order = new Order();
        Client client = new Client();
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from ORDERS where ID=?");
            preparedStatement.setLong(1, idEntity);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            order.setId(resultSet.getLong("id"));
            order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("status")));
            PreparedStatement preparedStatementForClient = connection.prepareStatement(
                    "select * from CLIENTS where ID=?");
            preparedStatementForClient.setLong(1, resultSet.getInt("client_id"));
            ResultSet resultSetForClient = preparedStatementForClient.executeQuery();

            resultSetForClient.next();
            client.setId(resultSetForClient.getLong("id"));
            client.setName(resultSetForClient.getString("name"));
            client.setSurname(resultSetForClient.getString("surname"));
            client.setPhoneNumber(resultSetForClient.getString("phone_number"));
            client.setEmail(resultSetForClient.getString("email"));
            order.setClient(client);

            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select ID from ORDERS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(get(resultSet.getLong("id")));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(long idEntity, Order updatedEntity) {
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update ORDERS set STATUS=?");
            preparedStatement.setString(1, updatedEntity.getOrderStatus().toString());

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(long idEntity) {
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete FROM ORDERS WHERE ID=?");
            preparedStatement.setLong(1, idEntity);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
