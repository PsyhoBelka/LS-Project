package ua.rozhkov.project.models;

import java.util.List;

public class Order {
    private long id;
    private int number;
    private Client client;
    private List<Product> products;
}
