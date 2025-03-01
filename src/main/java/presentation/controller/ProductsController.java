package presentation.controller;

import bll.ProductBLL;
import dao.ProductDAO;
import model.Product;
import presentation.view.ProductsOperationsPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa care controlează operațiile legate de produse din interfața grafică.
 */
public class ProductsController {
    private final ProductsOperationsPanel view;
    protected static final Logger LOGGER = Logger.getLogger(ProductsController.class.getName());
    /**
     * Constructorul care primește panoul de operații al produselor.
     * @param view Panoul de operații al produselor
     */
    public ProductsController(ProductsOperationsPanel view) {
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
        JButton refreshButton = view.getRefreshButton();
        adaugaButton.addActionListener(e -> {
            String nume = view.getNumeTextField().getText();
            int stock = Integer.parseInt(view.getStocTextField().getText());
            Product product = new Product(nume, stock);
            ProductBLL productBll = new ProductBLL();
            int id = productBll.insertProduct(product);
            if (id > 0) {
                productBll.findProductById(id);
            }
            try {
                productBll.findProductById(1);
            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }
            view.deleteProductsInTable();
            view.showProductsInTable();
        });
        editeazaButton.addActionListener(e -> {
            int id = Integer.parseInt(view.getIdEditareTextField().getText());
            String numeNou = view.getNumeEditareTextField().getText();
            int stockNou = Integer.parseInt(view.getStocEditareTextField().getText());

            Product editedProduct = new Product(id, numeNou, stockNou);
            ProductBLL productBLL = new ProductBLL();
            productBLL.update(editedProduct);
            view.deleteProductsInTable();
            view.showProductsInTable();
        });
        stergeButton.addActionListener(e -> {
            int id = Integer.parseInt(view.getIdStergereTextField().getText());
            ProductBLL productBLL = new ProductBLL();
            productBLL.delete(id);
            view.deleteProductsInTable();
            view.showProductsInTable();
        });
        refreshButton.addActionListener(e -> {
            view.deleteProductsInTable();
            view.showProductsInTable();
        });
    }
}
