package presentation.view;

import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;
import presentation.controller.ClientsController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 * Panoul de operații pentru gestionarea clienților în interfața grafică.
 */
public class ClientsOperationsPanel extends JPanel {
    private final JButton adaugaButton;
    private final JButton editeazaButton;
    private final JButton stergeButton;
    private final DefaultTableModel tableModel;
    private final JTextField numeTextField;
    private final JTextField adresaTextField;
    private final JTextField emailTextField;
    private final JTextField varstaTextField;
    private final JTextField idEditareTextField;
    private final JTextField numeEditareTextField;
    private final JTextField adresaEditareTextField;
    private final JTextField emailEditareTextField;
    private final JTextField varstaEditareTextField;
    private final JTextField idStergereTextField;
    /**
     * Constructor care inițializează panoul și toate componentele sale.
     */
    public ClientsOperationsPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(34, 139, 34));
        Font titleFont = new Font("Arial", Font.BOLD, 18);
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        JLabel adaugaClientLabel = new JLabel("Adaugare Client: Nume, Adresa, E-mail, Varsta");
        adaugaClientLabel.setFont(titleFont);
        adaugaClientLabel.setForeground(new Color(144, 238, 144));
        numeTextField = new JTextField(20);
        numeTextField.setFont(textFont);
        numeTextField.setText("Kulcsar Noemi");
        adresaTextField = new JTextField(20);
        adresaTextField.setFont(textFont);
        adresaTextField.setText("Strada Aleea Zorilor 6");
        emailTextField = new JTextField(20);
        emailTextField.setFont(textFont);
        emailTextField.setText("nomikulcsar@yahoo.com");
        varstaTextField = new JTextField(20);
        varstaTextField.setFont(textFont);
        varstaTextField.setText("20");
        adaugaButton = new JButton("Adauga");
        adaugaButton.setFont(textFont);
        adaugaButton.setBackground(new Color(144, 238, 144));

        JLabel editeazaClientLabel = new JLabel("Editeaza Client: ID, Nume, Adresa, E-mail, Varsta");
        editeazaClientLabel.setFont(titleFont);
        editeazaClientLabel.setForeground(new Color(144, 238, 144));
        idEditareTextField = new JTextField(5);
        idEditareTextField.setFont(textFont);
        idEditareTextField.setText("22");
        numeEditareTextField = new JTextField(20);
        numeEditareTextField.setFont(textFont);
        numeEditareTextField.setText("Kulcsar Noemi");
        adresaEditareTextField = new JTextField(20);
        adresaEditareTextField.setFont(textFont);
        adresaEditareTextField.setText("Strada Panselutelor 12");
        emailEditareTextField = new JTextField(20);
        emailEditareTextField.setFont(textFont);
        emailEditareTextField.setText("nomikulcsar@gmail.com");
        varstaEditareTextField = new JTextField(20);
        varstaEditareTextField.setFont(textFont);
        varstaEditareTextField.setText("21");
        editeazaButton = new JButton("Editeaza");
        editeazaButton.setFont(textFont);
        editeazaButton.setBackground(new Color(144, 238, 144));

        JLabel stergeClientLabel = new JLabel("Sterge Client: ID");
        stergeClientLabel.setFont(titleFont);
        stergeClientLabel.setForeground(new Color(144, 238, 144));
        idStergereTextField = new JTextField(5);
        idStergereTextField.setFont(textFont);
        idStergereTextField.setText("ID");
        stergeButton = new JButton("Sterge");
        stergeButton.setFont(textFont);
        stergeButton.setBackground(new Color(144, 238, 144));

        ClientDAO clientDAO = new ClientDAO();
        String[] columnNames = clientDAO.findColumns();
        Object[][] data = {};
        tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel adaugaPanel = new JPanel(new GridLayout(0, 2, 5, 5)); // 0 rânduri pentru a lăsa layout-ul să adauge cât mai multe rânduri necesare
        adaugaPanel.setBackground(new Color(34, 139, 34));
        adaugaPanel.add(adaugaClientLabel);
        adaugaPanel.add(new JLabel());
        adaugaPanel.add(numeTextField);
        adaugaPanel.add(adresaTextField);
        adaugaPanel.add(emailTextField);
        adaugaPanel.add(varstaTextField);
        adaugaPanel.add(new JLabel());
        adaugaPanel.add(adaugaButton);

        JPanel editeazaPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        editeazaPanel.setBackground(new Color(34, 139, 34));
        editeazaPanel.add(editeazaClientLabel);
        editeazaPanel.add(new JLabel());
        editeazaPanel.add(idEditareTextField);
        editeazaPanel.add(numeEditareTextField);
        editeazaPanel.add(adresaEditareTextField);
        editeazaPanel.add(emailEditareTextField);
        editeazaPanel.add(varstaEditareTextField);
        editeazaPanel.add(editeazaButton);

        JPanel stergePanel = new JPanel(new GridLayout(0, 2, 5, 5));
        stergePanel.setBackground(new Color(34, 139, 34));
        stergePanel.add(stergeClientLabel);
        stergePanel.add(new JLabel());
        stergePanel.add(idStergereTextField);
        stergePanel.add(new JLabel());
        stergePanel.add(stergeButton);

        JPanel operatiiPanel = new JPanel();
        operatiiPanel.setLayout(new BoxLayout(operatiiPanel, BoxLayout.Y_AXIS));
        operatiiPanel.setBackground(new Color(34, 139, 34));
        operatiiPanel.add(adaugaPanel);
        operatiiPanel.add(editeazaPanel);
        operatiiPanel.add(stergePanel);

        add(operatiiPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        showClientsInTable();
        ClientsController controller = new ClientsController(this);
    }

    public JButton getAdaugaButton() {
        return adaugaButton;
    }

    public JButton getEditeazaButton() {
        return editeazaButton;
    }

    public JButton getStergeButton() {
        return stergeButton;
    }

    public JTextField getNumeTextField() {
        return numeTextField;
    }

    public JTextField getAdresaTextField() {
        return adresaTextField;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JTextField getVarstaTextField() {
        return varstaTextField;
    }

    public JTextField getIdEditareTextField() {
        return idEditareTextField;
    }

    public JTextField getNumeEditareTextField() {
        return numeEditareTextField;
    }

    public JTextField getAdresaEditareTextField() {
        return adresaEditareTextField;
    }

    public JTextField getEmailEditareTextField() {
        return emailEditareTextField;
    }

    public JTextField getVarstaEditareTextField() {
        return varstaEditareTextField;
    }

    public JTextField getIdStergereTextField() {
        return idStergereTextField;
    }

    public void showClientsInTable() {
        ClientDAO clientDAO = new ClientDAO();
        List<Client> clients = clientDAO.findAll();
        for (Client client : clients) {
            Object[] row = {client.getId(), client.getName(), client.getAddress(), client.getEmail(), client.getAge()};
            tableModel.addRow(row);
        }
    }
    public void deleteClientsInTable()
    {
        tableModel.setRowCount(0);
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    public void deleteProductsInTable() {
        tableModel.setRowCount(0);
    }
}
