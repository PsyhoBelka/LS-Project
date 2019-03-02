package ua.rozhkov.project;

import ua.rozhkov.project.dao.impl.ClientDbDAOImpl;
import ua.rozhkov.project.dao.impl.OrderDAOImpl;
import ua.rozhkov.project.dao.impl.ProductDbDAOImpl;
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

        DatabaseService databaseService = DatabaseService.getInstance();
        //        ClientDbDAOImpl.getInstance().setDatabaseService(databaseService);
        //        ClientDbDAOImpl.getInstance().getAll();

        if (initServices()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            ((ProductServiceImpl) productService).setProductDAO(ProductDbDAOImpl.getInstance());

            ((ClientServiceImpl) clientService).setClientDAO(ClientDbDAOImpl.getInstance());
            ((ClientServiceImpl) clientService).setValidationService(ValidationServiceImpl.getInstance());

            ClientDbDAOImpl.getInstance().setDatabaseService(databaseService);
            ProductDbDAOImpl.getInstance().setDatabaseService(databaseService);

            ((OrderServiceImpl) orderService).setOrderDAO(OrderDAOImpl.getInstance());
            ((OrderServiceImpl) orderService).setProductDAO(ProductDbDAOImpl.getInstance());

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
