package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.ClientDAO;
import ua.rozhkov.project.models.Client;

public class ClientDAOImpl implements ClientDAO {
    @Override
    public boolean save(Client client) {
        System.out.println("Saving...");
        return true;
    }
}
