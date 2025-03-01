package presentation.controller;
import bll.ClientBLL;
import dao.ClientDAO;
import model.Client;
import presentation.view.ClientsOperationsPanel;
import start.ReflectionExample;
import javax.swing.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Clasa care controlează operațiile legate de clienți în interfața grafică.
 */
public class ClientsController {
    private final ClientsOperationsPanel view;
    protected static final Logger LOGGER = Logger.getLogger(ClientsController.class.getName());
    /**
     * Constructorul care primește panoul de operații al clienților.
     * @param view Panoul de operații al clienților
     */
    public ClientsController(ClientsOperationsPanel view) {
        this.view = view;
        attachListeners();
    }
    /**
     * Atașează ascultătorii pentru butoanele din interfață.
     */
    private void attachListeners() {
        JButton adaugaButton = view.getAdaugaButton();
        JButton editeazaButton = view.getEditeazaButton();
        JButton stergeButton = view.getStergeButton();
        adaugaButton.addActionListener(e -> {
            String nume = view.getNumeTextField().getText();
            String adresa = view.getAdresaTextField().getText();
            String email = view.getEmailTextField().getText();
            int varsta = Integer.parseInt(view.getVarstaTextField().getText());
            Client client = new Client(nume, adresa, email, varsta);
            ClientBLL clientBll = new ClientBLL();
            int id = clientBll.insertClient(client);
            if (id > 0) {
                clientBll.findClientById(id);
            }
            try {
                clientBll.findClientById(1);
            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }
            view.deleteClientsInTable();
            ClientBLL clientBLL = new ClientBLL();
            List<Client> clients = clientBLL.findAll();
            for (Client newClient : clients) {
                Object[] row = {newClient.getId(), newClient.getName(), newClient.getAddress(), newClient.getEmail(), newClient.getAge()};
                view.getTableModel().addRow(row);
            }
        });
        editeazaButton.addActionListener(e -> {
            int id = Integer.parseInt(view.getIdEditareTextField().getText());
            String numeNou = view.getNumeEditareTextField().getText();
            String adresaNoua = view.getAdresaEditareTextField().getText();
            String emailNou = view.getEmailEditareTextField().getText();
            int varstaNoua = Integer.parseInt(view.getVarstaEditareTextField().getText());
            Client editedClient = new Client(id, numeNou, adresaNoua, emailNou, varstaNoua);
            ClientBLL clientBLL = new ClientBLL();
            clientBLL.update(editedClient);
            view.deleteClientsInTable();
            view.showClientsInTable();
        });
        stergeButton.addActionListener(e -> {
            int id = Integer.parseInt(view.getIdStergereTextField().getText());
            ClientBLL clientBLL = new ClientBLL();
            clientBLL.delete(id);
            view.deleteClientsInTable();
            view.showClientsInTable();
        });
    }
}
