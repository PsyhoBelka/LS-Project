package ua.rozhkov.project.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMenu {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void show() throws IOException {
        boolean isRunning = true;

        while (isRunning) {
            showVariants();
            String input = bufferedReader.readLine();

            switch (input) {
                case "1":
                    System.out.println("1. Show products");
                    break;
                case "2":
                    System.out.println("2. Order product");
                    break;
                case "3":
                    System.out.println("3. Show bucket");
                    break;
                case "4":
                    System.out.println("4. Close order and buy");
                    break;
                case "9":
                    System.out.println("9. Return");
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
        System.out.println("1. Show products");
        System.out.println("2. Order product");
        System.out.println("3. Show bucket");
        System.out.println("4. Close order and buy");
        System.out.println("0. Exit&Return");
    }
}
