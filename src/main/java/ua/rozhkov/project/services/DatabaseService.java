package ua.rozhkov.project.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseService {
    //    private static final String DB_URL = "jdbc:h2:file:./db/shop";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private final String CLIENTS_TN = "CLIENTS";
    private final String PRODUCTS_TN = "PRODUCTS";
    private final String ORDERS_TN = "ORDERS";

    private static volatile DatabaseService instance;

    //    private final String clientsSQLFile = "create_clients.sql";
    //    private final String ordersSQLFile = "create_orders.sql";
    //    private final String productsSQLFile = "create_products.sql";

    public DatabaseService() {
        dropTables();

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

    public static DatabaseService getInstance() {
        if (instance == null)
            synchronized (DatabaseService.class) {
                if (instance == null)
                    instance = new DatabaseService();
            }
        return instance;
    }

    private void dropTables() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP table " + CLIENTS_TN + ";");
            statement.execute("DROP table " + ORDERS_TN + ";");
            statement.execute("DROP table " + PRODUCTS_TN + ";");
            System.out.println("Tables dropped!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        String urlDbFile = getClass().getResource("/db/shop.mv.db").getFile();
        urlDbFile = urlDbFile.substring(0, urlDbFile.length() - 6);
        String urlDb = "jdbc:h2:" + urlDbFile;
        try {
            return DriverManager.getConnection(urlDb, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean existTable(String tableName) {
        try (Connection connection = getConnection()) {
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
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader file = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + path)));
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return stringBuilder.toString();
    }

    private void createTable(String tableName, String SQL) {
        if (!SQL.isEmpty()) {
            try (
                    Connection connection = getConnection()
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
        } else
            System.out.println("Scripts not read!");
    }
}