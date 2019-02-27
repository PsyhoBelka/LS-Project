package ua.rozhkov.project;

import ua.rozhkov.project.dao.impl.ClientDAOImpl;
import ua.rozhkov.project.dao.impl.OrderDAOImpl;
import ua.rozhkov.project.dao.impl.ProductDAOImpl;
import ua.rozhkov.project.exceptions.BusinessException;
import ua.rozhkov.project.services.ClientService;
import ua.rozhkov.project.services.DatabaseService;
import ua.rozhkov.project.services.OrderService;
import ua.rozhkov.project.services.ProductService;
import ua.rozhkov.project.services.impl.ClientServiceImpl;
import ua.rozhkov.project.services.impl.OrderServiceImpl;
import ua.rozhkov.project.services.impl.ProductServiceImpl;
import ua.rozhkov.project.validators.ValidationService;
import ua.rozhkov.project.validators.impl.ValidationServiceImpl;
import ua.rozhkov.project.views.AdminMenu;
import ua.rozhkov.project.views.ClientMenu;
import ua.rozhkov.project.views.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    private static ValidationService validationService;
    private static ProductService productService;
    private static ClientService clientService;
    private static OrderService orderService;

    public static void main(String[] args) throws IOException, BusinessException {
        DatabaseService databaseService = new DatabaseService();
//        System.out.println(App.class.getClassLoader().getResource("create_clients.sql").getPath());
        if (initServices()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            ((ProductServiceImpl) productService).setProductDAO(ProductDAOImpl.getInstance());

            ((ClientServiceImpl) clientService).setClientDAO(ClientDAOImpl.getInstance());
            ((ClientServiceImpl) clientService).setValidationService(ValidationServiceImpl.getInstance());

            ((OrderServiceImpl) orderService).setOrderDAO(OrderDAOImpl.getInstance());
            ((OrderServiceImpl) orderService).setProductDAO(ProductDAOImpl.getInstance());

            AdminMenu adminMenu = new AdminMenu(bufferedReader, clientService, productService, orderService, validationService);
            ClientMenu clientMenu = new ClientMenu(bufferedReader, productService, orderService, validationService, clientService);

            MainMenu mainMenu = new MainMenu(bufferedReader, adminMenu, clientMenu);
            mainMenu.show();
        } else {
            System.out.println("Something went wrong! App closing!");
            System.exit(1);
        }
    }

    static boolean initServices() {
        try {
            validationService = ValidationServiceImpl.getInstance();
            productService = ProductServiceImpl.getInstance();
            clientService = ClientServiceImpl.getInstance();
            orderService = OrderServiceImpl.getInstance();
        } catch (Exception e) {
            System.out.println("Services not initialized!");
            return false;
        }
        return true;
    }
}
