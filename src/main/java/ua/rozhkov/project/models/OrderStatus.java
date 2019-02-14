package ua.rozhkov.project.models;

public enum OrderStatus {
    WAITING_FOR_PAYMENT("Wait for payment"),
    FORMED("Formed"),
    UNCOMPLETE("Uncomplete"),
    PAID("Paid"),
    DELIVERED("Delivered");

    String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
