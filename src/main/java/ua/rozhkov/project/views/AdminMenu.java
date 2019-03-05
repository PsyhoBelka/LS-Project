package ua.rozhkov.project.views;

import ua.rozhkov.project.exceptions.BusinessException;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.models.OrderStatus;
import ua.rozhkov.project.models.Product;
import ua.rozhkov.project.services.ClientService;
import ua.rozhkov.project.services.OrderService;
import ua.rozhkov.project.services.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

public class AdminMenu {
    private BufferedReader bufferedReader;
    private ProductService productService;
    private OrderService orderService;
    private ClientService clientService;


    public AdminMenu(BufferedReader bufferedReader, ProductService productService, OrderService orderService, ClientService clientService) {
        this.bufferedReader = bufferedReader;
        this.productService = productService;
        this.orderService = orderService;
        this.clientService = clientService;
    }

    public void show() throws IOException, BusinessException {
        boolean isRunning = true;
        while (isRunning) {
            showVariants();
            System.out.print("Enter you choice: ");
            String input = bufferedReader.readLine();
            switch (input) {
                case "1"://Create new client
                    createClient();
                    break;
                case "2"://Show info about client
                    showClientInfo();
                    break;
                case "3"://Show all clients
                    System.out.println("Registered clients:");
                    for (Client client : clientService.getAllClients()) {
                        System.out.println(client);
                    }
                    System.out.println();
                    break;
                case "4"://Edit client
                    editClient();
                    break;
                case "5"://Delete client
                    deleteClient();
                    break;
                case "11"://Create new product
                    createProduct();
                    break;
                case "12"://Show info about product
                    showProductInfo();
                    break;
                case "13"://Show all products
                    showAllProducts();
                    break;
                case "14"://Edit product
                    editProduct();
                    break;
                case "15"://Delete product
                    deleteProduct();
                    break;
                case "21"://Show all orders
                    orderService.getAllOrders();
                    break;
                case "22"://Update order status
                    updateOrderStatus();
                    break;
                case "23"://Delete order
                    deleteOrder();
                    break;

                case "R":
                    isRunning = false;
                    break;
                case "E":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input!!!");
            }
        }
    }

    private void showVariants() {
        /*CRUD Product+Client
         * RUD Order
         * */
        System.out.println("---Admin menu---");
        System.out.println("---Clients------");
        System.out.println("1. Create new client");
        System.out.println("2. Show info about client");
        System.out.println("3. Show all clients");
        System.out.println("4. Edit client");
        System.out.println("5. Delete client");
        System.out.println("---Products-----");
        System.out.println("11. Create new product");
        System.out.println("12. Show info about product");
        System.out.println("13. Show all products");
        System.out.println("14. Edit product");
        System.out.println("15. Delete product");
        System.out.println("---Orders-------");
        System.out.println("21. Show orders");
        System.out.println("22. Update order status");
        System.out.println("23. Delete order");
        System.out.println("----------------");
        System.out.println("R Return");
        System.out.println("E Exit");
        System.out.println("----------------");
    }

    private long getId(String s) throws IOException {
        System.out.print(s);
        return Long.parseLong(bufferedReader.readLine());
    }

    //CLIENT MENU METHODS
    private void createClient() throws IOException, BusinessException {
        System.out.print("Enter name: ");
        String clientName = bufferedReader.readLine();

        System.out.print("Enter surname: ");
        String clientSurname = bufferedReader.readLine();

        int clientAge;
        System.out.print("Enter age: ");
        clientAge = validateInputAge(bufferedReader.readLine());

        System.out.print("Enter phone number: ");
        String clientPhoneNumber = bufferedReader.readLine();

        System.out.print("Enter email: ");
        String clientEmail = bufferedReader.readLine();
        boolean res = clientService.createClient(clientName, clientSurname, clientAge, clientPhoneNumber, clientEmail);
        if (res) {
            System.out.println("Client created!");
        } else
            System.out.println("Client not created!");
        System.out.println();
    }

    private void showClientInfo() throws IOException {
        System.out.println(clientService.getClient(getId("Enter client id to view: ")));
        System.out.println();
    }

    private void editClient() throws IOException {
        long idClient = getId("Enter client id to edit: ");

        if (checkClientExist(idClient)) {
            System.out.println("Enter new data, leave blank to not change");

            System.out.print("Enter new name: ");
            String clientName = bufferedReader.readLine();

            System.out.print("Enter surname: ");
            String clientSurname = bufferedReader.readLine();

            System.out.print("Enter new age: ");
            String strClientAge = bufferedReader.readLine();
            int clientAge = 0;
            if (!strClientAge.isEmpty())
                clientAge = Integer.parseInt(clientName);

            System.out.print("Enter new phone number: ");
            String clientPhoneNumber = bufferedReader.readLine();

            System.out.print("Enter new email: ");
            String clientEmail = bufferedReader.readLine();

            boolean res = clientService.updateClient(idClient, clientName, clientSurname, clientAge, clientPhoneNumber, clientEmail);
            if (res)
                System.out.println("Client updated!");
            else
                System.out.println("Client not updated");
        } else
            System.out.println("Client not found!");
        System.out.println();
    }

    private void deleteClient() throws IOException {
        long idClient = getId("Enter client id to delete: ");
        if (checkClientExist(idClient)) {
            if (clientService.deleteClient(idClient))
                System.out.println("Client deleted!");
            else
                System.out.println("Client not deleted!");
        } else
            System.out.println("Client not found");
        System.out.println();
    }

    private boolean checkClientExist(long idClient) {
        return (idClient >= 0) && (clientService.getClient(idClient) != null);
    }

    //PRODUCT MENU METHODS
    private void createProduct() throws IOException {
        System.out.print("Enter name: ");
        String nameProduct = bufferedReader.readLine();

        System.out.print("Enter price: ");
        BigDecimal priceProduct = validateProductPrice(bufferedReader.readLine());

        boolean res = productService.createProduct(nameProduct, priceProduct);
        if (res)
            System.out.println("Product created!");
        else
            System.out.println("Product not created!");
        System.out.println();
    }

    private void showProductInfo() throws IOException {
        long idProduct = getId("Enter product id to view: ");

        if (checkProductExist(idProduct)) {
            System.out.println(productService.getProduct(idProduct));
        } else
            System.out.println("Wrong id!");

        System.out.println();
    }

    private void showAllProducts() {
        for (Product product : productService.getAllProducts()) {
            System.out.println(product);
        }
        System.out.println();
    }

    private void editProduct() throws IOException {
        long idProduct = getId("Enter product id to edit: ");

        if (checkProductExist(idProduct)) {
            System.out.println("Enter new data, leave blank to not change");

            System.out.print("Enter new name: ");
            String nameProduct = bufferedReader.readLine();

            System.out.print("Enter new price: ");
            BigDecimal priceProduct = validateProductPrice(bufferedReader.readLine());

            boolean res = productService.updateProduct(idProduct, nameProduct, priceProduct);
            if (res)
                System.out.println("Product updated!");
            else
                System.out.println("Product not updated!");
        } else
            System.out.println("Wrong id!");

        System.out.println();
    }

    private void deleteProduct() throws IOException {
        long idProduct = getId("Enter id to delete: ");

        if (checkProductExist(idProduct)) {
            if (productService.deleteProduct(idProduct)) {
                System.out.println("Deleted successfully!");
            }
        } else
            System.out.println("Wrong id!");

        System.out.println();
    }

    private boolean checkProductExist(long idProduct) {
        return ((idProduct >= 0) && (productService.getProduct(idProduct) != null));
    }

    //ORDER MENU METHODS
    private void updateOrderStatus() throws IOException {
        long idOrder = getId("Enter order id: ");

        if (checkOrderExist(idOrder)) {
            System.out.println("Choose new order status:");
            int statusNum = selectOrderStatus();
            orderService.updateOrderStatus(statusNum, OrderStatus.values()[statusNum]);
        } else
            System.out.println("Wrong id!");

        System.out.println();
    }

    private void deleteOrder() throws IOException {
        long idOrder = getId("Enter id to delete: ");

        if (checkOrderExist(idOrder)) {
            orderService.deleteOrder(idOrder);
            System.out.println("Order deleted!");
        } else
            System.out.println("Wrong id!");

        System.out.println();
    }

    private int selectOrderStatus() throws IOException {
        for (int i = 0; i < OrderStatus.values().length; i++) {
            System.out.println(i + 1 + ". " + OrderStatus.values()[i]);
        }
        System.out.println("You choice: ");
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException ex) {
            System.out.println("Wrong input!!!");
            System.out.print("Try again: ");
            return selectOrderStatus();
        }
    }

    private boolean checkOrderExist(long idOrder) {
        return ((idOrder >= 0) && (orderService.getOrder(idOrder) != null));
    }

    //Special methods
    private int validateInputAge(String input) throws IOException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.out.println("Wrong age!!!");
            System.out.print("Try again: ");
            return validateInputAge(bufferedReader.readLine());
        }
    }

    private BigDecimal validateProductPrice(String input) throws IOException {
        try {
            return BigDecimal.valueOf(Long.parseLong(input));
        } catch (NumberFormatException ex) {
            System.out.println("Wrong price!!!");
            System.out.println("Try again: ");
            return validateProductPrice(bufferedReader.readLine());
        }
    }
}
