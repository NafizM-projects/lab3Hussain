import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCustomerCRUD {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "IST888IST888";

    // Create (Insert) operation
    public void createCustomer(Customer customer) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            insertCustomer(connection, customer);
            System.out.println("Customer inserted into MySQL successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    private void insertCustomer(Connection connection, Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (customer_id, cust_firstname, cust_lastname, cust_state, cust_purchasetotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getState());
            preparedStatement.setDouble(5, customer.getPurchaseTotal());
            preparedStatement.executeUpdate();
        }
    }

    // Read all customers operation
    public List<Customer> readAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            customers = getAllCustomers(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return customers;
    }

    private List<Customer> getAllCustomers(Connection connection) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, cust_firstname, cust_lastname, cust_state, cust_purchasetotal FROM customer";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String firstName = resultSet.getString("cust_firstname");
                String lastName = resultSet.getString("cust_lastname");
                String state = resultSet.getString("cust_state");
                double purchaseTotal = resultSet.getDouble("cust_purchasetotal");
                customers.add(new Customer(customerId, firstName, lastName, state, purchaseTotal));
            }
        }
        return customers;
    }

    // Read by ID operation
    public Customer readCustomerById(int customerId) {
        Customer customer = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            customer = getCustomerById(connection, customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return customer;
    }

    private Customer getCustomerById(Connection connection, int customerId) throws SQLException {
        Customer customer = null;
        String sql = "SELECT customer_id, cust_firstname, cust_lastname, cust_state, cust_purchasetotal FROM customer WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("customer_id");
                    String firstName = resultSet.getString("cust_firstname");
                    String lastName = resultSet.getString("cust_lastname");
                    String state = resultSet.getString("cust_state");
                    double purchaseTotal = resultSet.getDouble("cust_purchasetotal");
                    customer = new Customer(id, firstName, lastName, state, purchaseTotal);
                }
            }
        }
        return customer;
    }

    // Update operation
    public void updateCustomer(Customer customer) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            updateCustomer(connection, customer);
            System.out.println("Customer updated in MySQL successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    private void updateCustomer(Connection connection, Customer customer) throws SQLException {
        String sql = "UPDATE customer SET cust_firstname = ?, cust_lastname = ?, cust_state = ?, cust_purchasetotal = ? WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getState());
            preparedStatement.setDouble(4, customer.getPurchaseTotal());
            preparedStatement.setInt(5, customer.getCustomerId());
            preparedStatement.executeUpdate();
        }
    }

    // Delete operation
    public void deleteCustomer(int customerId) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            deleteCustomer(connection, customerId);
            System.out.println("Customer deleted from MySQL successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    private void deleteCustomer(Connection connection, int customerId) throws SQLException {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        }
    }

    // Helper method to close connection
    private void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}