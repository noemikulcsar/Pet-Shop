package presentation;

import presentation.view.ClientsOperationsPanel;
import presentation.view.MainPagePanel;
import presentation.view.OrdersPanel;
import presentation.view.ProductsOperationsPanel;

import javax.swing.*;

/**
 * Interfața grafică pentru magazinul de animale.
 */
public class PetShopGUI extends JFrame {

    /**
     * Constructorul clasei PetShopGUI.
     * Inițializează fereastra principală a aplicației și adaugă tab-uri pentru diferitele operațiuni.
     */
    public PetShopGUI() {
        setTitle("Animal Land Pet Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Pagina Principala", new MainPagePanel());
        tabbedPane.addTab("Operatii Clienti", new ClientsOperationsPanel());
        ProductsOperationsPanel productPanel = new ProductsOperationsPanel();
        tabbedPane.addTab("Operatii Produse", productPanel);
        OrdersPanel ordersPanel = new OrdersPanel();
        tabbedPane.addTab("Plasare Comanda", ordersPanel);
        add(tabbedPane);
        setVisible(true);
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            if (selectedIndex == 2) {
                productPanel.updateProductTable();
            }
        });
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            if (selectedIndex == 3) {
                ordersPanel.updateProductList();
                ordersPanel.updateClientList();
            }
        });
    }
}
