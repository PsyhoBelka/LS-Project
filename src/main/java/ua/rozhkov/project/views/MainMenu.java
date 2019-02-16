package ua.rozhkov.project.views;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenu {
    private BufferedReader bufferedReader;
    private AdminMenu adminMenu;
    private ClientMenu clientMenu;

    //TODO: DI+IoC
    public MainMenu(BufferedReader bufferedReader, AdminMenu adminMenu, ClientMenu clientMenu) {
        this.bufferedReader = bufferedReader;
        this.adminMenu = adminMenu;
        this.clientMenu = clientMenu;
    }

    public void show() throws IOException {
        boolean isRunning = true;
        while (isRunning) {

            showVariants();
            System.out.print("Your choice: ");
            String input = bufferedReader.readLine();

            switch (input) {
                case "1"://Show admin menu
                    adminMenu.show();
                    break;
                case "2"://Show client menu
                    clientMenu.show();
                    break;

                case "E":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!!!");
                    break;
            }

            System.out.println();
        }
    }

    private void showVariants() {
        System.out.println("1. Admin");
        System.out.println("2. Client");
        System.out.println("E. Exit");
    }
}
