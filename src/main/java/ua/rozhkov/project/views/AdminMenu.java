package ua.rozhkov.project.views;

import ua.rozhkov.project.services.ClientService;
import ua.rozhkov.project.services.ProductService;
import ua.rozhkov.project.services.impl.ClientServiceImpl;
import ua.rozhkov.project.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class AdminMenu {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final ClientService clientService = new ClientServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    public void show() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            showVariants();
            String input = bufferedReader.readLine();
            switch (input) {
                case "1"://Create new client
                    createClient();
                    break;
                case "2"://Show info about client
                    showClientInfo();
                    break;
                case "3"://Show all clients
                    clientService.readAllClients();
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
                    productService.readAllProducts();
                    break;
                case "14"://Edit product
                    editProduct();
                    break;
                case "15"://Delete product
                    deleteProduct();
                    break;

                case "9":
                    isRunning = false;
                    break;
                case "0":
                    System.exit(1);
                    break;
                default:
                    System.out.println("Wrong input!!!");
            }
        }
    }

    private void showVariants() {
        /*CRUD
         * Create
         * Read
         * Read all
         * Update
         * Delete
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
        System.out.println("----------------");
        System.out.println("9. Return");
        System.out.println("0. Exit");
        System.out.println("----------------");
    }

    private long getId(String s) throws IOException {
        System.out.print(s);
        return Long.parseLong(bufferedReader.readLine());
    }

    //CLIENT MENU METHODS

    private void createClient() throws IOException {
        System.out.print("Enter name: ");
        String clientName = bufferedReader.readLine();

        System.out.print("Enter surname: ");
        String clientSurname = bufferedReader.readLine();

        System.out.print("Enter age: ");
        int clientAge = Integer.parseInt(bufferedReader.readLine());

        System.out.print("Enter phone number: ");
        String clientPhoneNumber = bufferedReader.readLine();

        System.out.print("Enter email: ");
        String clientEmail = bufferedReader.readLine();
        clientService.createClient(clientName, clientSurname, clientAge, clientPhoneNumber, clientEmail);
    }

    private void showClientInfo() throws IOException {
        clientService.readClient(getId("Enter client id to view: "));
    }

    private void editClient() throws IOException {
        long idClient = getId("Enter client id to edit: ");

        System.out.println("Enter new data, leave blank to not change");

        System.out.print("Enter new name: ");
        String clientName = bufferedReader.readLine();

        System.out.print("Enter surname: ");
        String clientSurname = bufferedReader.readLine();

        System.out.print("Enter new age: ");
        int clientAge = Integer.parseInt(bufferedReader.readLine());

        System.out.print("Enter new phone number: ");
        String clientPhoneNumber = bufferedReader.readLine();

        System.out.print("Enter new email: ");
        String clientEmail = bufferedReader.readLine();

        clientService.updateClient(idClient, clientName, clientSurname, clientAge, clientPhoneNumber, clientEmail);
    }

    private void deleteClient() throws IOException {
        long idClient = getId("Enter client id to delete: ");
        clientService.deleteClient(idClient);
    }

    //PRODUCT MENU METHODS
    private void createProduct() throws IOException {
        System.out.println("Enter name: ");
        String nameProduct = bufferedReader.readLine();

        System.out.println("Enter price: ");
        BigDecimal priceProduct = new BigDecimal(bufferedReader.readLine());

        productService.createProduct(nameProduct, priceProduct);
    }

    private void showProductInfo() throws IOException {
        productService.readProduct(getId("Enter product id to view: "));
    }

    private void editProduct() throws IOException {
        long idProduct = getId("Enter product id to edit: ");

        System.out.println("Enter new data, leave blank to not change");

        System.out.println("Enter new name: ");
        String nameProduct = bufferedReader.readLine();

        System.out.println("Enter new price: ");
        BigDecimal priceProduct = new BigDecimal(bufferedReader.readLine());

        productService.updateProduct(idProduct, nameProduct, priceProduct);
    }

    private void deleteProduct() throws IOException {
        productService.deleteProduct(getId("Enter id to delete: "));
    }
}
