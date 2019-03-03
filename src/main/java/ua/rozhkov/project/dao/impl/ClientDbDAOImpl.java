package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.ClientDAO;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.services.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDbDAOImpl implements ClientDAO {
    private static volatile ClientDbDAOImpl instance;
    private DatabaseService databaseService;

    public static ClientDbDAOImpl getInstance() {
        if (instance == null)
            synchronized (ClientDbDAOImpl.class) {
                if (instance == null)
                    instance = new ClientDbDAOImpl();
            }
        return instance;
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public boolean create(Client newEntity) {
        if (newEntity != null) {
            try (Connection connection = databaseService.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO PUBLIC.CLIENTS (NAME, SURNAME, AGE, PHONE_NUMBER, EMAIL) " +
                                "VALUES (?, ?, ?, ?, ?)");
                preparedStatement.setString(1, newEntity.getName());
                preparedStatement.setString(2, newEntity.getSurname());
                preparedStatement.setInt(3, newEntity.getAge());
                preparedStatement.setString(4, newEntity.getPhoneNumber());
                preparedStatement.setString(5, newEntity.getEmail());
                preparedStatement.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Client get(long idEntity) {
        if (idEntity > 0) {
            try (Connection connection = databaseService.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from CLIENTS where ID=?");
                preparedStatement.setInt(1, (int) idEntity);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                    return setClientFromResultSet(resultSet);
            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENTS");
            while (resultSet.next()) {
                clients.add(setClientFromResultSet(resultSet));
            }
            return clients;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(long idEntity, Client updatedEntity) {
        if ((idEntity > 0) && (updatedEntity != null)) {
            try (Connection connection = databaseService.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "update CLIENTS set NAME=?,SURNAME=?,AGE=?,PHONE_NUMBER=?,EMAIL=? where ID=?");
                preparedStatement.setString(1, updatedEntity.getName());
                preparedStatement.setString(2, updatedEntity.getSurname());
                preparedStatement.setInt(3, updatedEntity.getAge());
                preparedStatement.setString(4, updatedEntity.getPhoneNumber());
                preparedStatement.setString(5, updatedEntity.getEmail());
                preparedStatement.setInt(6, (int) idEntity);
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
                        "delete from CLIENTS where ID=?");
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

    private Client setClientFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            return new Client(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("email")
            );
        }
        return null;
    }
}
