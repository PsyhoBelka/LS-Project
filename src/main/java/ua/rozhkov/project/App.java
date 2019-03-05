package ua.rozhkov.project;

import ua.rozhkov.project.dao.impl.ClientDbDAOImpl;
import ua.rozhkov.project.dao.impl.OrderDbDAOImpl;
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
    private static DatabaseService databaseService;

    private static ProductService productService;
    private static ClientService clientService;
    private static OrderService orderService;

    private static ProductDbDAOImpl productDbDAO;
    private static ClientDbDAOImpl clientDbDAO;
    private static OrderDbDAOImpl orderDbDAO;

    public static void main(String[] args) throws IOException, BusinessException {

        if (initServices()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            productDbDAO.setDatabaseService(databaseService);
            clientDbDAO.setDatabaseService(databaseService);
            orderDbDAO.setDatabaseService(databaseService);

            ((ProductServiceImpl) productService).setProductDAO(productDbDAO);

            ((ClientServiceImpl) clientService).setClientDAO(clientDbDAO);
            ((ClientServiceImpl) clientService).setValidationService(validationService);

            ((OrderServiceImpl) orderService).setProductDAO(productDbDAO);
            ((OrderServiceImpl) orderService).setOrderDAO(orderDbDAO);

            AdminMenu adminMenu = new AdminMenu(bufferedReader, productService, orderService, clientService);
            ClientMenu clientMenu = new ClientMenu(bufferedReader, productService, orderService, clientService, validationService);

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
            databaseService = DatabaseService.getInstance();

            productService = ProductServiceImpl.getInstance();
            clientService = ClientServiceImpl.getInstance();
            orderService = OrderServiceImpl.getInstance();

            productDbDAO = ProductDbDAOImpl.getInstance();
            clientDbDAO = ClientDbDAOImpl.getInstance();
            orderDbDAO = OrderDbDAOImpl.getInstance();

        } catch (Exception e) {
            System.out.println("Services not initialized!");
            return false;
        }
        return true;
    }
}
