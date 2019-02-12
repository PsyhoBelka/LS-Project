package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.dao.ClientDAO;
import ua.rozhkov.project.dao.impl.ClientDAOImpl;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientDAO clientDAO = new ClientDAOImpl();

    @Override
    public boolean createClient(String name, String surname, int age, String phoneNumber, String email) {
        Client newClient = new Client(name, surname, age, phoneNumber, email);
        boolean result = clientDAO.create(newClient);
        if (result) {
            System.out.println("Client created! " + newClient);
        }
        return result;
    }

    @Override
    public Client readClient(long idClient) {
        clientDAO.get(idClient);
        return null;
    }

    @Override
    public List<Client> readAllClients() {
        clientDAO.getAll();
        return null;
    }

    @Override
    public boolean updateClient(long idClient, String name, String surname, int age, String phoneNumber, String email) {
        Client updatedClient = readClient(idClient);
        if (!name.equals("")) updatedClient.setName(name);
        if (!surname.equals("")) updatedClient.setSurname(surname);
        if (age != 0) updatedClient.setAge(age);
        if (!phoneNumber.equals("")) updatedClient.setPhoneNumber(phoneNumber);
        if (!email.equals("")) updatedClient.setEmail(email);
        boolean result = clientDAO.update(idClient, updatedClient);
        if (result) System.out.println("Client updated!");
        return result;
    }

    @Override
    public boolean deleteClient(long idClient) {
        boolean result = clientDAO.delete(idClient);
        if (result){
            System.out.println("Client deleted!");
        }
        return result;
    }

}
