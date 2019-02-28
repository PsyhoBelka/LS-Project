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
    private final String GET_ALL_CLIENTS = "SELECT * FROM CLIENTS";
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
    public long create(Client newEntity) {
        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO PUBLIC.CLIENTS (NAME, SURNAME, AGE, PHONENUMBER, EMAIL) " +
                            "VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.setString(2, newEntity.getSurname());
            preparedStatement.setInt(3, newEntity.getAge());
            preparedStatement.setString(4, newEntity.getPhoneNumber());
            preparedStatement.setString(5, newEntity.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Client get(long idEntity) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_CLIENTS);
            while (resultSet.next()) {
                Client client = new Client();
                client.setName(resultSet.getString("name"));
                client.setSurname(resultSet.getString("surname"));
                client.setAge(resultSet.getInt("age"));
                client.setPhoneNumber(resultSet.getString("phoneNumber"));
                client.setEmail(resultSet.getString("email"));
                clients.add(client);
            }
            return clients;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(long idEntity, Client updatedEntity) {
        return false;
    }

    @Override
    public boolean delete(long idEntity) {
        return false;
    }
}
