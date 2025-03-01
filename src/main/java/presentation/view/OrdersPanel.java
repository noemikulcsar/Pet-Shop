package presentation.view;

import dao.ClientDAO;
import dao.ProductDAO;
import model.Bill;
import model.Client;
import model.Product;
import presentation.controller.OrdersController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panoul pentru efectuarea comenzilor, afișând listele de clienți și produse, câmpul pentru cantitate,
 * butoanele pentru plasarea comenzii și actualizarea listelor, precum și un tabel cu produsele disponibile.
 */
public class OrdersPanel extends JPanel {
    private final JList<String> clientList;
    private final JList<String> productList;
    private final JTextField quantityTextField;
    private final JButton orderButton;
    private final JButton refreshButton;
    private final DefaultTableModel tableModel;
    /**
     * Constructor care inițializează panoul pentru efectuarea comenzilor și adaugă componentele necesare.
     */
    public OrdersPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(34, 139, 34));
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font textFont = new Font("Arial", Font.PLAIN, 14);

        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        topPanel.setBackground(new Color(34, 139, 34));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(34, 139, 34));
        JLabel clientLabel = new JLabel("Client");
        clientLabel.setFont(labelFont);
        clientLabel.setForeground(new Color(144, 238, 144));
        clientList = new JList<>();
        JScrollPane clientScrollPane = new JScrollPane(clientList);
        leftPanel.add(clientLabel, BorderLayout.NORTH);
        leftPanel.add(clientScrollPane, BorderLayout.CENTER);
        topPanel.add(leftPanel);

        JPanel middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(new Color(34, 139, 34));
        JLabel quantityLabel = new JLabel("Cantitate");
        quantityLabel.setFont(labelFont);
        quantityLabel.setForeground(new Color(144, 238, 144));
        quantityTextField = new JTextField(10);
        quantityTextField.setFont(textFont);
        middlePanel.add(quantityLabel);
        middlePanel.add(quantityTextField);
        topPanel.add(middlePanel);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(34, 139, 34));
        JLabel productLabel = new JLabel("Produs");
        productLabel.setFont(labelFont);
        productLabel.setForeground(new Color(144, 238, 144));
        productList = new JList<>();
        JScrollPane productScrollPane = new JScrollPane(productList);
        rightPanel.add(productLabel, BorderLayout.NORTH);
        rightPanel.add(productScrollPane, BorderLayout.CENTER);
        topPanel.add(rightPanel);

        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(new Color(34, 139, 34));
        orderButton = new JButton("Comanda");
        orderButton.setFont(labelFont);
        orderButton.setBackground(new Color(144, 238, 144));
        bottomPanel.add(orderButton);
        refreshButton = new JButton("Refresh");
        refreshButton.setFont(labelFont);
        refreshButton.setBackground(new Color(144, 238, 144));
        bottomPanel.add(refreshButton);
        add(bottomPanel, BorderLayout.SOUTH);

        ProductDAO productDAO = new ProductDAO();
        String[] columnNames = productDAO.findColumns();
        Object[][] data = {};
        tableModel = new DefaultTableModel(data, columnNames);
        JTable productsTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(productsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        Font tableFont = productsTable.getFont();
        clientList.setFont(tableFont);
        productList.setFont(tableFont);

        showProductsInList();
        showClientsInList();
        showProductsInTable();

        OrdersController controller = new OrdersController(this);
    }

    /**
     * Metodă pentru afișarea produselor în tabel.
     */
    public void showProductsInTable() {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.findAll();
        for (Product product : products) {
            Object[] row = {product.getId(), product.getName(), product.getStock()};
            tableModel.addRow(row);
        }
    }
    /**
     * Metodă pentru afișarea produselor în lista de produse.
     */
    public void showProductsInList() {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.findAll();
        DefaultListModel<String> aux = new DefaultListModel<>();
        for (Product product : products) {
            String item = product.getName();
            aux.addElement(item);
        }
        productList.setModel(aux);
    }
    /**
     * Metodă pentru afișarea clienților în lista de clienți.
     */
    public void showClientsInList() {
        ClientDAO clientDAO = new ClientDAO();
        List<Client> clients = clientDAO.findAll();
        DefaultListModel<String> aux = new DefaultListModel<>();
        for (Client client : clients) {
            String item = client.getName();
            aux.addElement(item);
        }
        clientList.setModel(aux);
    }
    /**
     * Metodă pentru ștergerea produselor din tabel.
     */
    public void deleteProductsInTable() {
        tableModel.setRowCount(0);
    }
    /**
     * Metodă pentru obținerea listei de clienți.
     */
    public JList<String> getClientList() {
        return clientList;
    }
    /**
     * Metodă pentru obținerea listei de produse.
     */
    public JList<String> getProductList() {
        return productList;
    }
    /**
     * Metodă pentru obținerea câmpului pentru introducerea cantității.
     */
    public JTextField getQuantityTextField() {
        return quantityTextField;
    }
    /**
     * Metodă pentru obținerea butonului pentru plasarea comenzii.
     */
    public JButton getOrderButton() {
        return orderButton;
    }
    /**
     * Metodă pentru obținerea modelului tabelului.
     */
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    /**
     * Metodă pentru obținerea butonului pentru actualizarea listelor.
     */
    public JButton getRefreshButton() {
        return refreshButton;
    }

    public void updateProductList() {
        DefaultListModel<String> productListModel = (DefaultListModel<String>) productList.getModel();
        productListModel.removeAllElements();
        showProductsInList();
    }
    public void updateClientList() {
        DefaultListModel<String> clientListModel = (DefaultListModel<String>) clientList.getModel();
        clientListModel.removeAllElements();
        showClientsInList();
    }
}
