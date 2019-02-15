package ua.rozhkov.project.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final AdminMenu adminMenu = new AdminMenu();
    private final ClientMenu clientMenu = new ClientMenu();

    //TODO: DI+IoC

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

                case "0":
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
        System.out.println("0. Exit");
    }
}
