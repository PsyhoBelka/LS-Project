package ua.rozhkov.project.views;

import ua.rozhkov.project.exceptions.BusinessException;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.services.ClientService;
import ua.rozhkov.project.services.OrderService;
import ua.rozhkov.project.services.ProductService;
import ua.rozhkov.project.validators.ValidationService;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientMenu {
    private BufferedReader bufferedReader;
    private ProductService productService;
    private OrderService orderService;
    private ClientService clientService;
    private ValidationService validationService;

    private Client currentClient = null;

    public ClientMenu(BufferedReader bufferedReader, ProductService productService, OrderService orderService, ClientService clientService, ValidationService validationService) {
        this.bufferedReader = bufferedReader;
        this.productService = productService;
        this.orderService = orderService;
        this.clientService = clientService;
        this.validationService = validationService;
    }

    public void show() throws IOException, BusinessException {
        boolean isRunning = true;

        while (isRunning) {
            showVariants();
            System.out.print("Enter you choice: ");
            String input = bufferedReader.readLine();

            switch (input) {
                case "1"://1. Register or sign in
                    currentClient = registerClient();
                    break;
                case "2"://2. Show products
                    productService.getAllProducts();
                    break;
                case "3"://3. Order products
                    orderProducts(currentClient);
                    break;
                case "4"://4. Show orders
                    showClientOrders(currentClient);
                    break;
                case "5"://Logout
                    currentClient = null;
                    System.out.println("Successfully logout!");
                    System.out.println();
                    break;
                case "R"://Return
                    isRunning = false;
                    break;
                case "E"://Exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input!!!");
            }
        }
    }

    private void orderProducts(Client client) throws IOException, BusinessException {
        if (client == null) {
            currentClient = findClient();
            if (currentClient == null)
                registerClient();
        } else {
            System.out.print("Enter id's of product you want to order (ex: 1,2,3,4): ");
            String input = bufferedReader.readLine();
            String[] idsList = input.split(",\\s+|,|\\s+,|\\s+");
            long[] ids = new long[idsList.length];
            for (int i = 0; i < idsList.length - 1; i++) {
                ids[i] = Long.parseLong(idsList[i]);
            }
            orderService.createOrder(currentClient, ids);
            System.out.println("Order created!");
        }
        System.out.println();
    }

    private Client findClient(String... clientPhonenumber) throws IOException {
        String currClPhoneNum;
        if (clientPhonenumber.length > 1) {
            currClPhoneNum = clientPhonenumber[0];
        } else {
            System.out.print("Enter you phoneNumber(10 digits): ");
            currClPhoneNum = bufferedReader.readLine();
        }

        if (validationService.validatePhoneNum(currClPhoneNum)) {
            for (Client client : clientService.getAllClients()) {
                if (client.getPhoneNumber().equals(currClPhoneNum))
                    return client;
            }
        } else
            System.out.println("Wrong phone number! You unauthorized!");
        return null;
    }

    private Client registerClient() throws IOException, BusinessException {
        System.out.println("You are not register yet? Register now to order!");
        System.out.print("Enter name: ");
        String clientName = bufferedReader.readLine();

        System.out.print("Enter surname: ");
        String clientSurname = bufferedReader.readLine();

        System.out.print("Enter phone number: ");
        String clientPhoneNumber = bufferedReader.readLine();

        boolean res = clientService.createClient(clientName, clientSurname, clientPhoneNumber);
        if (res) {
            System.out.println("Client created!");
            System.out.println("Now you can sign in!");
        }
        System.out.println();
        return null;
    }

    private void showClientOrders(Client client) {
        //TODO fix here
        /*if (client != null) {
            for (Order order : orderService.getAllOrders()) {
                if (order.getClient().equals(client))
                    System.out.println(order);
            }
        } else
            System.out.println("No orders yet!");
        System.out.println();*/
    }

    private void showVariants() {
        System.out.println("1. Register");
        System.out.println("2. Show products");
        System.out.println("3. Order products");
        System.out.println("4. Show orders");
        System.out.println("5. Logout");
        System.out.println("-------------");
        System.out.println("R. Return");
        System.out.println("E. Exit");
    }
}
