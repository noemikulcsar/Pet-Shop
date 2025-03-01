package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

/**
 * The AbstractDAO class provides generic database operations for DAO implementations.
 * @param <T> The type of object handled by the DAO.
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    /**
     * Constructs a new AbstractDAO instance.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        // Retrieves the type of T using reflection
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Creates the SELECT query for finding an object by its ID.
     * @return The SELECT query string.
     */
    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + "id" + " =?");
        return sb.toString();
    }

    /**
     * Creates the SELECT query for retrieving all objects.
     * @return The SELECT query string.
     */
    private String createClassicSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Finds an object by its ID.
     * @param id The ID of the object to find.
     * @return The object with the specified ID, or null if not found.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            List<T> objects = createObjects(resultSet);
            if (!objects.isEmpty()) {
                return objects.get(0);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Retrieves the column names of the table represented by the DAO's type parameter.
     *
     * @return An array of strings containing the column names of the table.
     *         Returns an empty array if an error occurs or if the table has no columns.
     */
    public String[] findColumns() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;
        String query = createClassicSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();
            String[] columns = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columns[i - 1] = metaData.getColumnName(i);
            }
            return columns;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error finding columns for table " + type.getSimpleName() + ": " + e.getMessage());
            return new String[0]; // Return an empty array in case of exception
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Retrieves all objects from the database.
     * @return A list of all objects in the database.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createClassicSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<T> objects = createObjects(resultSet);
            return objects;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of exception
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creates objects from the ResultSet obtained from a database query.
     * @param resultSet The ResultSet containing the data from the database.
     * @return A list of objects created from the ResultSet.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    try {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(instance, value);
                    } catch (IntrospectionException e) {
                        LOGGER.log(Level.WARNING, "Error creating objects from ResultSet: " + e.getMessage());
                    }
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | SQLException e) {
            LOGGER.log(Level.WARNING, "Error creating objects from ResultSet: " + e.getMessage());
        }
        return list;
    }

    /**
     * Inserts a new object into the database.
     * @param t The object to insert.
     * @return The inserted object, or null if the operation fails.
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement insertStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String tableName = type.getSimpleName().toLowerCase();
            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            Method[] methods = type.getDeclaredMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get") && !methodName.equals("getClass")) {
                    String fieldName = methodName.substring(3).toLowerCase();
                    columns.append(fieldName).append(", ");
                    values.append("?, ");
                }
            }
            columns.delete(columns.length() - 2, columns.length());
            values.delete(values.length() - 2, values.length());
            String insertQuery = "INSERT INTO `" + tableName + "` (" + columns + ") VALUES (" + values + ")";
            insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get") && !methodName.equals("getClass")) {
                    Object value = method.invoke(t);
                    insertStatement.setObject(parameterIndex++, value);
                }
            }
            insertStatement.executeUpdate();
            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                Method setIdMethod = type.getMethod("setId", int.class);
                setIdMethod.invoke(t, generatedId);
            }
            return t;
        } catch (SQLException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.log(Level.WARNING, "Error inserting " + type.getSimpleName() + ": " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Updates an existing object in the database.
     * @param t The object to update.
     * @return The updated object, or null if the operation fails.
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String tableName = type.getSimpleName().toLowerCase();
            StringBuilder setClause = new StringBuilder();
            Method[] methods = type.getDeclaredMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get") && !methodName.equals("getClass")) {
                    String fieldName = methodName.substring(3).toLowerCase();
                    setClause.append(fieldName).append(" = ?, ");
                }
            }
            setClause.delete(setClause.length() - 2, setClause.length());
            String updateQuery = "UPDATE " + tableName + " SET " + setClause + " WHERE id = ?";
            updateStatement = connection.prepareStatement(updateQuery);
            int parameterIndex = 1;
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get") && !methodName.equals("getClass")) {
                    Object value = method.invoke(t);
                    updateStatement.setObject(parameterIndex++, value);
                }
            }
            Method getIdMethod = type.getMethod("getId");
            Object idValue = getIdMethod.invoke(t);
            updateStatement.setObject(parameterIndex, idValue);
            updateStatement.executeUpdate();
            return t;
        } catch (SQLException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.log(Level.WARNING, "Error updating " + type.getSimpleName() + ": " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Deletes an object from the database by its ID.
     * @param id The ID of the object to delete.
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String deleteQuery = "DELETE FROM " + type.getSimpleName() + " WHERE id = ?";
            deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error deleting " + type.getSimpleName() + ": " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(connection);
        }
    }
}
