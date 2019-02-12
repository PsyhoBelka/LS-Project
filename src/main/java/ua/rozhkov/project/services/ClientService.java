package ua.rozhkov.project.services;

public interface ClientService {
    /**
     *
     * @param name
     * @param surname
     * @param phoneNumber
     * @return
     */
    boolean createClient(String name, String surname, String phoneNumber);
    void deleteClient();

}
