package presentation.controller;
import bll.OrderBLL;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import dao.BillDAO;
import dao.ClientDAO;
import dao.ProductDAO;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;
import presentation.view.OrdersPanel;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.*;
import java.time.format.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
/**
 * Clasa care controlează operațiile legate de comenzile din interfața grafică.
 */
public class OrdersController {
    private final OrdersPanel view;
    protected static final Logger LOGGER = Logger.getLogger(OrdersController.class.getName());
    /**
     * Constructorul care primește panoul de operații al comenzilor.
     * @param view Panoul de operații al comenzilor
     */
    public OrdersController(OrdersPanel view) {
        this.view = view;
        attachListeners();
    }
    /**
     * Atașează ascultătorii pentru butoanele din interfață.
     */
    private void attachListeners() {
        JButton comandaButton = view.getOrderButton();
        comandaButton.addActionListener(e -> {
            String clientName = view.getClientList().getSelectedValue();
            String productName = view.getProductList().getSelectedValue();
            int quantity = Integer.parseInt(view.getQuantityTextField().getText());
            Client client = ClientDAO.findByName(clientName);
            Product product = ProductDAO.findByName(productName);
            if (client == null) {
                LOGGER.log(Level.WARNING, "Clientul " + clientName + " nu a fost gasit.");
                return;
            }
            if (product == null) {
                LOGGER.log(Level.WARNING, "Produsul " + productName + " nu a fost gasit.");
                return;
            }
            if ((product.getStock() - quantity) >= 0 && quantity > 0) {
                Order order = new Order(quantity, client.getId(), product.getId());
                OrderBLL orderBLL = new OrderBLL();
                int id = orderBLL.insertOrder(order);
                JOptionPane.showMessageDialog(view, "Comanda adaugata cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
                product.setStock(product.getStock() - quantity);
                ProductDAO productDAO = new ProductDAO();
                productDAO.update(product);
                Bill bill = new Bill(id, clientName, productName, quantity, LocalDateTime.now());
                BillDAO billDAO = new BillDAO();
                billDAO.insert(bill);
                Document document = new Document();
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("Bills.pdf"));
                } catch (DocumentException | FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                document.open();
                Font fontTitlu = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 18, BaseColor.BLACK);
                Paragraph titlu = new Paragraph("Comenzi:\n\n", fontTitlu);
                titlu.setAlignment(Element.ALIGN_CENTER);
                try {
                    document.add(titlu);
                } catch (DocumentException ex) {
                    throw new RuntimeException(ex);
                }
                List<Bill> bills = billDAO.findAll();
                for(Bill aux : bills)
                {
                    Font fontComanda = FontFactory.getFont(FontFactory.TIMES_ITALIC, 14, BaseColor.BLACK);
                    LocalDateTime myDateObj = aux.timeStamp();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = myDateObj.format(myFormatObj);
                    Paragraph comanda = new Paragraph("No: " + aux.orderNumber() + "\n" + "Client: " + aux.clientName() + "\n" +
                            "Product: " + aux.productName() + "\n" + "Quantity: " + aux.quantity() + "\n" +
                            "Time: " + formattedDate + "\n\n", fontComanda);
                    try {
                        document.add(comanda);
                    } catch (DocumentException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                document.close();
            } else {
                JOptionPane.showMessageDialog(view, "Eroare la adăugarea comenzii!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
            view.deleteProductsInTable();
            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.findAll();
            for (Product newProduct : products) {
                Object[] row = {newProduct.getId(), newProduct.getName(), newProduct.getStock()};
                view.getTableModel().addRow(row);
            }
        });
        JButton refreshButton = view.getRefreshButton();
        refreshButton.addActionListener(e -> {
            view.deleteProductsInTable();
            view.showProductsInTable();
            view.showProductsInList();
            view.showClientsInList();
        });
    }
}
