package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;

/**
 * The ClientDAO class provides database operations specific to the Client model.
 */
public class ClientDAO extends AbstractDAO<Client> {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private final static String findByNameStatementString = "SELECT * FROM client WHERE name = ?";

    /**
     * Finds a client by their name.
     * @param clientName The name of the client to find.
     * @return The client with the specified name, or null if not found.
     */
    public static Client findByName(String clientName) {
        Client toReturn = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findByNameStatementString);
            findStatement.setString(1, clientName);
            rs = findStatement.executeQuery();
            if (rs.next()) {
                int id_client = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                toReturn = new Client(id_client, name, address, email, age);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
