package ua.rozhkov.project.views;

import ua.rozhkov.project.services.ClientService;
import ua.rozhkov.project.services.impl.ClientServiceImpl;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMenu {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private ClientService clientService=new ClientServiceImpl();

    public void show() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
        showVariants();
        String input = bufferedReader.readLine();
            switch (input) {
                case "1":
                    createClient();
                    break;
                case "2":
                    System.out.println("Add product");
                    break;
                case "3":
                    System.out.println("Remove client");
                    break;
                case "4":
                    System.out.println("List all clients");
                    break;
                case "9":
                    isRunning = false;
                    break;
                case "0":
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("Wrong input!!!");
            }
        }
    }

    private void createClient() throws IOException {
        System.out.print("Enter name: ");
        String clientName=bufferedReader.readLine();
        System.out.print("Enter surname: ");
        String clientSurname=bufferedReader.readLine();
        System.out.print("Enter phone number: ");
        String clientPhoneNumber=bufferedReader.readLine();
        clientService.createClient(clientName,clientSurname,clientPhoneNumber);
    }

    private void showVariants() {
        System.out.println();
        System.out.println("1. Add client");
        System.out.println("2. Add product");
        System.out.println("3. Remove client");
        System.out.println("4. List all clients");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }
}
