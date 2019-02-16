package ua.rozhkov.project.services;

import ua.rozhkov.project.exceptions.BusinessException;
import ua.rozhkov.project.models.Client;

import java.util.List;

public interface ClientService {
    /**
     * Create new client
     *
     * @param name        name of new client
     * @param surname     surname of new client
     * @param age         age of client
     * @param phoneNumber phone number of new client
     * @param email       client's email
     * @return id of newly created client
     */
    long createClient(String name, String surname, int age, String phoneNumber, String email) throws BusinessException;

    /**
     * Show client's info
     *
     * @param idClient id client for show
     * @return instance of Client with specified Id
     */
    Client readClient(long idClient);

    /**
     * Show all exist clients
     *
     * @return list of Clients
     */
    List<Client> readAllClients();

    /**
     * Change client's info
     *
     * @param idClient    client's id for editing
     * @param name        new name of new client
     * @param surname     new surname of new client
     * @param age         new age of client
     * @param phoneNumber new phone number of new client
     * @param email       new client's email
     * @return true, if operation successfully
     */
    boolean updateClient(long idClient, String name, String surname, int age, String phoneNumber, String email);

    /**
     * Delete specified client
     *
     * @param idClient client's id to delete
     * @return true, if operation successfully
     */
    boolean deleteClient(long idClient);

}
