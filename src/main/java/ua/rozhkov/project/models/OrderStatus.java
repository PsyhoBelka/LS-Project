package ua.rozhkov.project.models;

public enum OrderStatus {
    WAITING_FOR_PAYMENT("Wait for payment"),
    FORMED("Formed"),
    UNCOMPLETED("Uncompleted"),
    PAID("Paid"),
    DELIVERED("Delivered");

    String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
