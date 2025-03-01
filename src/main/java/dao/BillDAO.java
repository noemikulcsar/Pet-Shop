package dao;

import connection.ConnectionFactory;
import model.Bill;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillDAO {
    private static final Logger LOGGER = Logger.getLogger(BillDAO.class.getName());
    private final static String findAllStatementString = "SELECT * FROM bill";
    private final static String insertStatementString = "INSERT INTO bill (orderNumber, clientName, productName, quantity, timeStamp) VALUES (?, ?, ?, ?, ?)";

    /**
     * Găsește toate facturile din baza de date.
     * @return O listă de facturi, sau o listă goală dacă nu există facturi în baza de date.
     */
    public static List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllStatement = null;
        ResultSet rs = null;
        try {
            findAllStatement = dbConnection.prepareStatement(findAllStatementString);
            rs = findAllStatement.executeQuery();
            while (rs.next()) {
                int orderNumber = rs.getInt("orderNumber");
                String clientName = rs.getString("clientName");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                Timestamp timeStamp = rs.getTimestamp("timeStamp");
                LocalDateTime localDateTime = timeStamp.toLocalDateTime();
                bills.add(new Bill(orderNumber, clientName, productName, quantity, localDateTime));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return bills;
    }

    /**
     * Inserează o nouă factură în baza de date.
     * @param bill Factura de inserat.
     * @return True dacă inserarea a fost reușită, false în caz contrar.
     */
    public static void insert(Bill bill) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, bill.orderNumber());
            insertStatement.setString(2, bill.clientName());
            insertStatement.setString(3, bill.productName());
            insertStatement.setInt(4, bill.quantity());
            insertStatement.setTimestamp(5, Timestamp.valueOf(bill.timeStamp()));
            int rowsInserted = insertStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
