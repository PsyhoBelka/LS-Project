package ua.rozhkov.project.views;

import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.services.impl.OrderServiceImpl;
import ua.rozhkov.project.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMenu {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final ProductServiceImpl productService = ProductServiceImpl.getInstance();
    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();

    public void show() throws IOException {
        boolean isRunning = true;

        while (isRunning) {
            showVariants();
            System.out.print("Enter you choice: ");
            String input = bufferedReader.readLine();

            switch (input) {
                case "1"://1. Show products
                    productService.readAllProducts();
                    break;
                case "2"://2. Order products
                    orderProducts();
                    break;
                case "3"://3. Show bucket
                    break;
                case "4"://4. Close order and buy
                    break;
                case "9"://9. Return
                    isRunning = false;
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input!!!");
            }
        }
    }

    private void orderProducts() throws IOException {
        System.out.print("Enter id's of product you want to order (ex: 1,2,3,4): ");
        String input = bufferedReader.readLine();
        String[] idsList = input.split(",\\s+|,|\\s+,|\\s+");
        long[] ids = new long[idsList.length];
        for (int i = 0; i < idsList.length - 1; i++) {
            ids[i] = Long.parseLong(idsList[i]);
        }
        orderService.createOrder(new Client("111", "111", 21, "11", "11"), ids);
    }

    private void showVariants() {
        System.out.println("1. Show products");
        System.out.println("2. Order products");
//        System.out.println("3. Show bucket");
//        System.out.println("4. Close order and buy");
        System.out.println("0. Exit&Return");
    }
}
