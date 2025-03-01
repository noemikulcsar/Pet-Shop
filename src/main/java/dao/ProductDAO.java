package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Product;

/**
 * Data Access Object pentru gestionarea entităților de tip Product în baza de date.
 */
public class ProductDAO extends AbstractDAO<Product> {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private final static String findByNameStatementString = "SELECT * FROM product where name = ?";

    /**
     * Găsește un produs în baza de date după numele său.
     * @param productName Numele produsului de căutat.
     * @return Produsul găsit sau null dacă nu a fost găsit niciun produs cu acel nume.
     */
    public static Product findByName(String productName) {
        Product toReturn = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findByNameStatementString);
            findStatement.setString(1, productName);
            rs = findStatement.executeQuery();
            if (rs.next()) {
                int id_product = rs.getInt("id");
                String name = rs.getString("name");
                int stock = rs.getInt("stock");
                toReturn = new Product(id_product, name, stock);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
