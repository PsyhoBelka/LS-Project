package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.dao.ClientDAO;
import ua.rozhkov.project.exceptions.BusinessException;
import ua.rozhkov.project.models.Client;
import ua.rozhkov.project.services.ClientService;
import ua.rozhkov.project.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static volatile ClientService instance;

    private ClientDAO clientDAO;
    private ValidationService validationService;

    private ClientServiceImpl() {
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
    public boolean createClient(String name, String surname, int age, String phoneNumber, String email) {
        if (!validationService.validateEmail(email)) {
            System.out.println("Wrong email format!");
            return false;

        }
        if (!validationService.validatePhoneNum(phoneNumber)) {
            System.out.println("Wrong phone number format!");
            return false;
        }
        if (!validationService.validateAge(age)) {
            System.out.println("Wrong age!");
            return false;
        }

        for (Client client : getAllClients()) {
            if (client.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Duplicate phone number!");
                return false;
            }
        }

        return clientDAO.create(new Client(0, name, surname, age, phoneNumber, email));
    }

    @Override
    public boolean createClient(String clientName, String clientSurname, String clientPhoneNumber) throws BusinessException {
        if (!validationService.validatePhoneNum(clientPhoneNumber)) {
            for (Client client : getAllClients()) {
                if (client.getPhoneNumber().equals(clientPhoneNumber))
                    throw new BusinessException("Duplicate phone number");
            }
            return clientDAO.create(new Client(clientName, clientSurname, clientPhoneNumber));
        }
        return false;
    }

    @Override
    public Client getClient(long idClient) {
        return clientDAO.get(idClient);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDAO.getAll();
    }

    @Override
    public boolean updateClient(long idClient, String name, String surname, int age, String phoneNumber, String email) {
        if (idClient > 0) {
            Client updatedClient = getClient(idClient);
            if (!name.isEmpty())
                updatedClient.setName(name);
            if (!surname.isEmpty())
                updatedClient.setSurname(surname);
            if (age != 0 && validationService.validateAge(age)) {
                updatedClient.setAge(age);
                System.out.println("Wrong age!");
                return false;
            }

            if (!phoneNumber.isEmpty() && validationService.validatePhoneNum(phoneNumber)) {
                updatedClient.setPhoneNumber(phoneNumber);
                System.out.println("Wrong phone number!");
                return false;
            }
            if (!email.isEmpty() && validationService.validateEmail(email)) {
                updatedClient.setEmail(email);
                System.out.println("Wrong email!");
                return false;
            }
            return clientDAO.update(idClient, updatedClient);
        }
        return false;
    }

    @Override
    public boolean deleteClient(long idClient) {
        return clientDAO.delete(idClient);
    }

}
