package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.ProductDAO;
import ua.rozhkov.project.models.Product;
import ua.rozhkov.project.services.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDbDAOImpl implements ProductDAO {
    private static volatile ProductDbDAOImpl instance;
    private DatabaseService databaseService;

    public ProductDbDAOImpl() {
    }


    public static ProductDbDAOImpl getInstance() {
        if (instance == null) {
            synchronized (ProductDbDAOImpl.class) {
                if (instance == null) {
                    instance = new ProductDbDAOImpl();
                }
                return instance;
            }
        }
        return instance;
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public boolean create(Product newEntity) {
        if (newEntity != null) {
            try (Connection connection = databaseService.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO PUBLIC.PRODUCTS (NAME,PRICE) " +
                                "VALUES (?, ?)");
                preparedStatement.setString(1, newEntity.getName());
                preparedStatement.setBigDecimal(2, newEntity.getPrice());
                boolean res = preparedStatement.execute();
                if (res)
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Product get(long idEntity) {
        if (idEntity > 0) {
            try (Connection connection = databaseService.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from PRODUCTS where ID=?");
                preparedStatement.setInt(1, (int) idEntity);
                ResultSet resultSet = preparedStatement.executeQuery();
                return setProductFromResultSet(resultSet);
            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS");
            while (resultSet.next()) {
                products.add(setProductFromResultSet(resultSet));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(long idEntity, Product updatedEntity) {
        if ((idEntity > 0) && (updatedEntity != null)) {
            try (Connection connection = databaseService.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "update PRODUCTS set NAME=?,PRICE=?");
                preparedStatement.setString(1, updatedEntity.getName());
                preparedStatement.setBigDecimal(2, updatedEntity.getPrice());
                if (preparedStatement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(long idEntity) {
        if (idEntity > 0) {
            try (Connection connection = databaseService.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "delete from PRODUCTS where ID=?");
                preparedStatement.setLong(1, idEntity);
                if (preparedStatement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private Product setProductFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            return new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getBigDecimal("price")
            );
        }
        return null;
    }
}
