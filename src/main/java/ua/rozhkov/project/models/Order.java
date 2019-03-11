package ua.rozhkov.project.models;

import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private long id;
    private OrderStatus orderStatus;

    private Client client;
    private List<Product> products;

    public Order(Client client) {
        this.client = client;
    }

    public Order() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatus=" + orderStatus.getStatus() +
                ", client=" + client.toString() +
                ", products=" + products.stream().map(product -> (product.toString())).collect(Collectors.joining()) +
                '}';
    }
}
