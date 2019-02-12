package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.ClientDAO;
import ua.rozhkov.project.models.Client;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    @Override
    public long create(Client newEntity) {
        System.out.println("Client created! "+newEntity);
        return 0;
    }

    @Override
    public Client get(long idEntity) {
        System.out.println("Some client info");
        return null;
    }

    @Override
    public List<Client> getAll() {
        System.out.println("client info 1");
        System.out.println("client info 2");
        System.out.println("client info 3");
        return null;
    }

    @Override
    public boolean update(long idEntity, Client updatedEntity) {
        System.out.println("Client info updated!");
        return true;
    }

    @Override
    public boolean delete(long idEntity) {
        System.out.println("Client deleted!");
        return true;
    }
}
