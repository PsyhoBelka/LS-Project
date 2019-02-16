package ua.rozhkov.project;

import ua.rozhkov.project.dao.impl.ClientDAOImpl;
import ua.rozhkov.project.dao.impl.OrderDAOImpl;
import ua.rozhkov.project.dao.impl.ProductDAOImpl;
import ua.rozhkov.project.services.ClientService;
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
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        ValidationService validationService = ValidationServiceImpl.getInstance();

        ProductService productService = ProductServiceImpl.getInstance();
        ((ProductServiceImpl) productService).setProductDAO(ProductDAOImpl.getInstance());

        ClientService clientService = ClientServiceImpl.getInstance();
        ((ClientServiceImpl) clientService).setClientDAO(ClientDAOImpl.getInstance());
        ((ClientServiceImpl) clientService).setValidationService(ValidationServiceImpl.getInstance());

        OrderService orderService = OrderServiceImpl.getInstance();
        ((OrderServiceImpl) orderService).setOrderDAO(OrderDAOImpl.getInstance());
        ((OrderServiceImpl) orderService).setProductDAO(ProductDAOImpl.getInstance());

        AdminMenu adminMenu = new AdminMenu(bufferedReader, clientService, productService, orderService, validationService);
        ClientMenu clientMenu = new ClientMenu(bufferedReader, productService, orderService);

        MainMenu mainMenu = new MainMenu(bufferedReader, adminMenu, clientMenu);
        mainMenu.show();
    }
}
