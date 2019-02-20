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
    public long createClient(String name, String surname, int age, String phoneNumber, String email) throws BusinessException {
        if (validationService.validateEmail(email) &&
                validationService.validatePhoneNum(phoneNumber) &&
                validationService.validateAge(age)) {
            for (Client client : readAllClients()) {
                if (client.getPhoneNumber().equals(phoneNumber))
                    throw new BusinessException("Duplicate phone number");
            }
            return new Client(name, surname, age, phoneNumber, email).getId();
        }
        return -1;
    }

    @Override
    public long createClient(String clientName, String clientSurname, String clientPhoneNumber) throws BusinessException {
        if (validationService.validatePhoneNum(clientPhoneNumber)) {
            for (Client client : readAllClients()) {
                if (client.getPhoneNumber().equals(clientPhoneNumber))
                    throw new BusinessException("Duplicate phone number");
            }
            return new Client(clientName, clientSurname, clientPhoneNumber).getId();
        }
        return -1;
    }

    @Override
    public Client readClient(long idClient) {
        if (idClient >= 0)
            return clientDAO.get(idClient);
        return null;
    }

    @Override
    public List<Client> readAllClients() {
        return clientDAO.getAll();
    }

    @Override
    public boolean updateClient(long idClient, String name, String surname, int age, String phoneNumber, String email) {
        if (idClient >= 0) {
            Client updatedClient = readClient(idClient);
            if (!name.isEmpty())
                updatedClient.setName(name);
            if (!surname.isEmpty())
                updatedClient.setSurname(surname);
            try {
                if (age != 0 && validationService.validateAge(age))
                    updatedClient.setAge(age);
            } catch (BusinessException e) {
                e.printStackTrace();
                System.out.println("Wrong age!");
                return false;
            }
            try {
                if (!phoneNumber.isEmpty() && validationService.validatePhoneNum(phoneNumber))
                    updatedClient.setPhoneNumber(phoneNumber);
            } catch (BusinessException e) {
                e.printStackTrace();
                System.out.println("Wrong phone number!");
                return false;
            }
            try {
                if (!email.isEmpty() && validationService.validateEmail(email))
                    updatedClient.setEmail(email);
            } catch (BusinessException e) {
                e.printStackTrace();
                System.out.println("Wrong email!");
                return false;
            }
            return clientDAO.update(idClient, updatedClient);
        }
        return false;
    }

    @Override
    public boolean deleteClient(long idClient) {
        if (idClient >= 0)
            return clientDAO.delete(idClient);
        return false;
    }

}
