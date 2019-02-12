package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.dao.ClientDAO;
import ua.rozhkov.project.dao.impl.ClientDAOImpl;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.services.ClientService;

public class ClientServiceImpl implements ClientService {
    private ClientDAO clientDAO=new ClientDAOImpl();

    @Override
    public boolean createClient(String name, String surname, String phoneNumber) {
        Client newClient=new Client(name,surname,phoneNumber);
        boolean result=clientDAO.save(newClient);
        if (result){
            System.out.println("Client created! "+ newClient);
        }
        return result;
    }

    @Override
    public void deleteClient() {

    }
}
