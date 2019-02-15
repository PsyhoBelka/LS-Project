package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.ClientDAO;
import ua.rozhkov.project.models.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private static volatile ClientDAOImpl instance;

    private static List<Client> clientsT = new ArrayList<Client>();
    private static long index = 0;

    private ClientDAOImpl() {
    }

    public static ClientDAOImpl getInstance() {
        if (instance == null)
            synchronized (ClientDAOImpl.class) {
                if (instance == null)
                    instance = new ClientDAOImpl();
            }
        return instance;
    }

    @Override
    public long create(Client newEntity) {
//        System.out.println("Client created! " + newEntity);
        newEntity.setId(getId());
        clientsT.add(newEntity);
        return newEntity.getId();
    }

    @Override
    public Client get(long idEntity) {
//        System.out.println("Some client info");
        Client tmp = null;
        for (Client client : clientsT) {
            if (client.getId() == idEntity) {
                tmp = client;
                break;
            }
        }
        return tmp;
    }

    @Override
    public List<Client> getAll() {
//        System.out.println("client info 1");
//        System.out.println("client info 2");
//        System.out.println("client info 3");

        return clientsT;
    }

    @Override
    public boolean update(long idEntity, Client updatedEntity) {
//        System.out.println("Client info updated!");
        Client tmp = get(idEntity);
        if (tmp != null) {
            updatedEntity.setId(tmp.getId());
            tmp = updatedEntity;
            return true;
        } else return true;
    }

    @Override
    public boolean delete(long idEntity) {
        System.out.println("Client deleted!");
        return true;
    }

    //temporary methods for emulating non-existed database
    private long getId() {
        return index++;
    }
}
