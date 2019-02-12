package ua.rozhkov.project.views;

import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.services.ClientService;
import ua.rozhkov.project.services.impl.ClientServiceImpl;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMenu {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private ClientService clientService = new ClientServiceImpl();

    public void show() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            showVariants();
            String input = bufferedReader.readLine();
            switch (input) {
                case "1":
//                    System.out.println("1. Create new client");
                    createClient();
                    break;
                case "2":
//                    System.out.println("2. Show info about client");
                    showClientInfo();
                    break;
                case "3":
//                    System.out.println("3. Show all clients");
                    clientService.readAllClients();
                    break;
                case "4":
//                    System.out.println("4. Edit client");
                    editClient();
                    break;
                case "5":
//                    System.out.println("5. Delete client");
                    deleteClient();
                    break;
                case "11":
                    System.out.println("11. Create new product");
                    break;
                case "12":
                    System.out.println("12. Show info about product");
                    break;
                case "13":
                    System.out.println("13. Show all products");
                    break;
                case "14":
                    System.out.println("14. Edit product");
                    break;
                case "15":
                    System.out.println("15. Delete product");
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

    private long getIdClient(String s) throws IOException {
        System.out.print(s);
        return Long.parseLong(bufferedReader.readLine());
    }

    private void deleteClient() throws IOException {
        long idClient = getIdClient("Enter client id to delete: ");
        clientService.deleteClient(idClient);
    }

    private void showClientInfo() throws IOException {
        long idClient = getIdClient("Enter client id to view: ");
        clientService.readClient(idClient);
    }

    private void editClient() throws IOException {
        long idClient = getIdClient("Enter client id to update: ");

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

    private void createClient() throws IOException {
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
        clientService.createClient(clientName, clientSurname, clientAge, clientPhoneNumber, clientEmail);
    }

    private void showVariants() {
        /*
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
        System.out.println("---Products------");
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
}
