package bll;

import java.util.ArrayList;
import java.util.List;

import bll.validators.ClientAgeValidator;
import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

/**
 * The ClientBLL class provides business logic operations for managing clients.
 */
public class ClientBLL
{
    private final List<Validator<Client>> validators;

    /**
     * Constructs a new ClientBLL object with default validators.
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());
    }

    /**
     * Finds a client by their ID.
     *
     * @param id The ID of the client to find.
     * @return The client with the specified ID, or null if not found.
     */
    public Client findClientById(int id) {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findById(id);
    }

    /**
     * Inserts a new client into the database.
     *
     * @param client The client object to insert.
     * @return The ID of the inserted client.
     */
    public int insertClient(Client client) {
        // Validate client data
        for (Validator<Client> v : validators) {
            v.validate(client);
        }

        ClientDAO clientDAO = new ClientDAO();
        Client insertedClient = clientDAO.insert(client);
        return insertedClient.getId();
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client The client object to update.
     * @return The updated client object.
     */
    public Client update(Client client)
    {
        // Validate client data
        for(Validator<Client> v : validators){
            v.validate(client);
        }

        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.update(client);
    }

    /**
     * Deletes a client from the database.
     *
     * @param id The ID of the client to delete.
     */
    public void delete(int id)
    {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.delete(id);
    }

    public List<Client> findAll() {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findAll();
    }
}
