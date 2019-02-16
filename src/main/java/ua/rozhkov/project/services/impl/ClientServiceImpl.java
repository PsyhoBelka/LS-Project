package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.dao.ClientDAO;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.services.ClientService;
import ua.rozhkov.project.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static volatile ClientService instance;

    private ClientDAO clientDAO;
    private ValidationService validationService;

    //TODO: use custom BusinessException


    public ClientServiceImpl() {
    }

    public static void setInstance(ClientService instance) {
        ClientServiceImpl.instance = instance;
    }

    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public static ClientService getInstance() {
        if (instance == null)
            synchronized (ClientServiceImpl.class) {
                if (instance == null)
                    instance = new ClientServiceImpl();
            }
        return instance;
    }

    @Override
    public long createClient(String name, String surname, int age, String phoneNumber, String email) {

        Client newClient = new Client(name, surname, age, phoneNumber, email);
        return clientDAO.create(newClient);
    }

    @Override
    public Client readClient(long idClient) {
        return clientDAO.get(idClient);
    }

    @Override
    public List<Client> readAllClients() {
        return clientDAO.getAll();
    }

    @Override
    public boolean updateClient(long idClient, String name, String surname, int age, String phoneNumber, String email) {
        Client updatedClient = readClient(idClient);
        if (!name.isEmpty()) updatedClient.setName(name);
        if (!surname.isEmpty()) updatedClient.setSurname(surname);
        if (age != 0) updatedClient.setAge(age);
        if (!phoneNumber.isEmpty()) updatedClient.setPhoneNumber(phoneNumber);
        if (!email.isEmpty()) updatedClient.setEmail(email);
        return clientDAO.update(idClient, updatedClient);
    }

    @Override
    public boolean deleteClient(long idClient) {
        return clientDAO.delete(idClient);
    }

}
