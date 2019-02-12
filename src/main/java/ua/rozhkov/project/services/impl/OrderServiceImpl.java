package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.models.Order;
import ua.rozhkov.project.services.OrderService;

import java.math.BigDecimal;

public class OrderServiceImpl implements OrderService {

    @Override
    public long createOrder(Client clientForOrder) {
        return 0;
    }

    @Override
    public Order readOrder(long id) {
        return null;
    }

    @Override
    public BigDecimal calculateOrder(long id) {
        return null;
    }

    @Override
    public boolean addToOrder(long id, long idProductToAdd) {
        return false;
    }

    @Override
    public boolean removeFromOrder(long id, long idProductToRemove) {
        return false;
    }

    @Override
    public boolean deleteOrder(long id) {
        return false;
    }
}
