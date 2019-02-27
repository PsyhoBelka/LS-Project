package ua.rozhkov.project.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseService {
    private static final String DB_URL = "jdbc:h2:file:C:/temp/db-file";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private final String CLIENTS_TN = "CLIENTS";
    private final String PRODUCTS_TN = "PRODUCTS";
    private final String ORDERS_TN = "ORDERS";

//    private final String clientsSQLFile = "create_clients.sql";
//    private final String ordersSQLFile = "create_orders.sql";
//    private final String productsSQLFile = "create_products.sql";

    public DatabaseService() {

        if (!existTable(CLIENTS_TN)) {
            createTable(CLIENTS_TN, readSQLFile(CLIENTS_TN));
        } else
            System.out.println("Table " + CLIENTS_TN + " already exist!");

        if (!existTable(ORDERS_TN)) {
            createTable(ORDERS_TN, readSQLFile(ORDERS_TN));
        } else
            System.out.println("Table " + ORDERS_TN + " already exist!");

        if (!existTable(PRODUCTS_TN)) {
            createTable(PRODUCTS_TN, readSQLFile(PRODUCTS_TN));
        } else
            System.out.println("Table " + PRODUCTS_TN + " already exist!");

        System.out.println();
        System.out.println();
        System.out.println();
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean existTable(String tableName) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String readSQLFile(String tableName) {
        String path = "create_" + tableName.toLowerCase() + ".sql";
        File file = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            System.out.println("File " + path + " not found!");
        }
        return "";
    }

    private void createTable(String tableName, String SQL) {
        try (
                Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)
        ) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(SQL);
                System.out.println("Table " + tableName + " created");

            } catch (SQLException e) {
                System.out.println("---Table not created!---");
                e.printStackTrace();
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("---Connection troubles---");
            e.printStackTrace();
            System.out.println("-------------------------");
        }
    }
}