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
    private ValidationService validationService;
    private ClientService clientService;
    private Client currentClient = null;

    public ClientMenu(BufferedReader bufferedReader, ProductService productService, OrderService orderService, ValidationService validationService, ClientService clientService) {
        this.bufferedReader = bufferedReader;
        this.productService = productService;
        this.orderService = orderService;
        this.validationService = validationService;
        this.clientService = clientService;
    }

    public void show() throws IOException {
        boolean isRunning = true;

        while (isRunning) {
            showVariants();
            System.out.print("Enter you choice: ");
            String input = bufferedReader.readLine();

            switch (input) {
                case "1"://Register or sign in
                    //TODO: register
                    break;
                case "2"://1. Show products
                    productService.readAllProducts();
                    break;
                case "3"://2. Order products
                    orderProducts(currentClient);
                    break;
                case "4"://3. Show orders
                    //TODO: show products
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

    private void orderProducts(Client client) throws IOException {
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
            long newOrderId = orderService.createOrder(currentClient, ids);
            System.out.println("Total price of order: " + orderService.calculateOrder(newOrderId));
        }
        System.out.println();
    }

    private Client findClient() throws IOException {
        System.out.println("Enter you phoneNumber(10 digits): ");
        String input = bufferedReader.readLine();
        try {
            validationService.validatePhoneNum(input);
            for (Client client : clientService.readAllClients()) {
                if (client.getPhoneNumber().equals(input))
                    return client;
            }
        } catch (BusinessException ex) {
            ex.printStackTrace();
        }
        System.out.println("Wrong phone number! You unauthorized!");
        return null;
    }

    private Client registerClient() {
        //TODO: do method
    }


    private void showVariants() {
        System.out.println("1. Register or sign in");
        System.out.println("2. Show products");
        System.out.println("3. Order products");
        System.out.println("4. Show orders");
        System.out.println("-------------");
        System.out.println("R. Return");
        System.out.println("E. Exit");
    }
}
