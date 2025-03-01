package presentation.view;

import dao.ClientDAO;
import dao.ProductDAO;
import model.Product;
import presentation.controller.ProductsController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panoul pentru operațiile pe produse, inclusiv adăugarea, editarea și ștergerea produselor, precum și afișarea acestora într-un tabel.
 */
public class ProductsOperationsPanel extends JPanel {
    private final JButton adaugaButton;
    private final JButton editeazaButton;
    private final JButton stergeButton;
    private final JButton refreshButton;
    private final DefaultTableModel tableModel;
    private final JTextField numeTextField;
    private final JTextField stocTextField;
    private final JTextField idEditareTextField;
    private final JTextField numeEditareTextField;
    private final JTextField stocEditareTextField;
    private final JTextField idStergereTextField;

    /**
     * Constructor care inițializează panoul pentru operațiile pe produse și adaugă componentele necesare.
     */
    public ProductsOperationsPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(34, 139, 34));
        Font titleFont = new Font("Arial", Font.BOLD, 18);
        Font textFont = new Font("Arial", Font.PLAIN, 14);

        JLabel adaugaProductLabel = new JLabel("Adaugare Produs: Nume, Stoc");
        adaugaProductLabel.setFont(titleFont);
        adaugaProductLabel.setForeground(new Color(144, 238, 144));
        numeTextField = new JTextField(20);
        numeTextField.setFont(textFont);
        numeTextField.setText("Cocker Spaniel");
        stocTextField = new JTextField(20);
        stocTextField.setFont(textFont);
        stocTextField.setText("12");
        adaugaButton = new JButton("Adauga");
        adaugaButton.setFont(textFont);
        adaugaButton.setBackground(new Color(144, 238, 144));

        JLabel editeazaProductLabel = new JLabel("Editeaza Produs: ID, Nume, Stoc");
        editeazaProductLabel.setFont(titleFont);
        editeazaProductLabel.setForeground(new Color(144, 238, 144));
        idEditareTextField = new JTextField(5);
        idEditareTextField.setFont(textFont);
        idEditareTextField.setText("13");
        numeEditareTextField = new JTextField(20);
        numeEditareTextField.setFont(textFont);
        numeEditareTextField.setText("Cocker Spaniel");
        stocEditareTextField = new JTextField(20);
        stocEditareTextField.setFont(textFont);
        stocEditareTextField.setText("20");
        editeazaButton = new JButton("Editeaza");
        editeazaButton.setFont(textFont);
        editeazaButton.setBackground(new Color(144, 238, 144));

        JLabel stergeProductLabel = new JLabel("Sterge Produs: ID");
        stergeProductLabel.setFont(titleFont);
        stergeProductLabel.setForeground(new Color(144, 238, 144));
        idStergereTextField = new JTextField(5);
        idStergereTextField.setFont(textFont);
        idStergereTextField.setText("ID");
        stergeButton = new JButton("Sterge");
        stergeButton.setFont(textFont);
        stergeButton.setBackground(new Color(144, 238, 144));

        ProductDAO productDAO = new ProductDAO();
        String[] columnNames = productDAO.findColumns();
        Object[][] data = {};
        tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel adaugaPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        adaugaPanel.setBackground(new Color(34, 139, 34));
        adaugaPanel.add(adaugaProductLabel);
        adaugaPanel.add(new JLabel());
        adaugaPanel.add(numeTextField);
        adaugaPanel.add(stocTextField);
        adaugaPanel.add(new JLabel());
        adaugaPanel.add(adaugaButton);

        JPanel editeazaPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        editeazaPanel.setBackground(new Color(34, 139, 34));
        editeazaPanel.add(editeazaProductLabel);
        editeazaPanel.add(new JLabel());
        editeazaPanel.add(idEditareTextField);
        editeazaPanel.add(numeEditareTextField);
        editeazaPanel.add(stocEditareTextField);
        editeazaPanel.add(new JLabel());
        editeazaPanel.add(editeazaButton);

        JPanel stergePanel = new JPanel(new GridLayout(0, 2, 5, 5));
        stergePanel.setBackground(new Color(34, 139, 34));
        stergePanel.add(stergeProductLabel);
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

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(textFont);
        refreshButton.setBackground(new Color(144, 238, 144));
        JPanel refreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Aliniere la dreapta
        refreshPanel.add(refreshButton);
        refreshPanel.setBackground(new Color(34, 139, 34));
        add(refreshPanel, BorderLayout.SOUTH);

        showProductsInTable();
        ProductsController controller = new ProductsController(this);
    }

    /**
     * Returnează butonul pentru adăugarea unui produs.
     * @return Butonul pentru adăugarea unui produs
     */
    public JButton getAdaugaButton() {
        return adaugaButton;
    }

    /**
     * Returnează butonul pentru editarea unui produs.
     * @return Butonul pentru editarea unui produs
     */
    public JButton getEditeazaButton() {
        return editeazaButton;
    }

    /**
     * Returnează butonul pentru ștergerea unui produs.
     * @return Butonul pentru ștergerea unui produs
     */
    public JButton getStergeButton() {
        return stergeButton;
    }

    /**
     * Returnează câmpul text pentru numele produsului.
     * @return Câmpul text pentru numele produsului
     */
    public JTextField getNumeTextField() {
        return numeTextField;
    }

    /**
     * Returnează câmpul text pentru stocul produsului.
     * @return Câmpul text pentru stocul produsului
     */
    public JTextField getStocTextField() {
        return stocTextField;
    }

    /**
     * Returnează câmpul text pentru ID-ul produsului de editat.
     * @return Câmpul text pentru ID-ul produsului de editat
     */
    public JTextField getIdEditareTextField() {
        return idEditareTextField;
    }

    /**
     * Returnează câmpul text pentru noul nume al produsului.
     * @return Câmpul text pentru noul nume al produsului
     */
    public JTextField getNumeEditareTextField() {
        return numeEditareTextField;
    }

    /**
     * Returnează câmpul text pentru noul stoc al produsului.
     * @return Câmpul text pentru noul stoc al produsului
     */
    public JTextField getStocEditareTextField() {
        return stocEditareTextField;
    }

    /**
     * Returnează câmpul text pentru ID-ul produsului de șters.
     * @return Câmpul text pentru ID-ul produsului de șters
     */
    public JTextField getIdStergereTextField() {
        return idStergereTextField;
    }

    /**
     * Returnează butonul pentru actualizarea listei de produse.
     * @return Butonul pentru actualizarea listei de produse
     */
    public JButton getRefreshButton() {
        return refreshButton;
    }

    /**
     * Metodă care afișează produsele în tabel.
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
     * Metodă care șterge toate produsele din tabel.
     */
    public void deleteProductsInTable() {
        tableModel.setRowCount(0);
    }
    public void updateProductTable() {
        deleteProductsInTable();
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.findAll();
        for (Product newProduct : products) {
            Object[] row = {newProduct.getId(), newProduct.getName(), newProduct.getStock()};
            tableModel.addRow(row);
        }
    }
}
