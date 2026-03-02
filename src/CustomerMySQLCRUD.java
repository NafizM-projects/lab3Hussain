/** Project: Solo Lab3 Database
 * Purpose Details: code for SQL crud
 * Course: IST 242
 * Author: Nafiz Hussain
 * Date Developed: 2/22/2026
 * Last Date Changed: 3/1/2026
 * Rev: 3

 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMySQLCRUD {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "IST888IST888"; // Change to your MySQL password

    public void insertCustomer(Customer customer) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO customer (customer_id, cust_firstname, cust_lastname, cust_state, cust_purchasetotal, cust_purchasetype, cust_paymentmethod) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, customer.getCustomerId());
                preparedStatement.setString(2, customer.getFirstName());
                preparedStatement.setString(3, customer.getLastName());
                preparedStatement.setString(4, customer.getState());
                preparedStatement.setDouble(5, customer.getPurchaseTotal());
                preparedStatement.setString(6, customer.getPurchaseType());
                preparedStatement.setString(7, customer.getPaymentMethod());
                preparedStatement.executeUpdate();
                System.out.println("Customer inserted into MySQL successfully!");
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void readAllCustomers() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "SELECT customer_id, cust_firstname, cust_lastname, cust_state, cust_purchasetotal, cust_purchasetype, cust_paymentmethod FROM customer";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("customer_id");
                    String firstName = resultSet.getString("cust_firstname");
                    String lastName = resultSet.getString("cust_lastname");
                    String state = resultSet.getString("cust_state");
                    double purchaseTotal = resultSet.getDouble("cust_purchasetotal");
                    String purchaseType = resultSet.getString("cust_purchasetype");
                    String paymentMethod = resultSet.getString("cust_paymentmethod");

                    System.out.println("Customer{" +
                            "customerId=" + id +
                            ", firstName='" + firstName + '\'' +
                            ", lastName='" + lastName + '\'' +
                            ", state='" + state + '\'' +
                            ", purchaseTotal=" + purchaseTotal +
                            ", purchaseType='" + purchaseType + '\'' +
                            ", paymentMethod='" + paymentMethod + '\'' +
                            '}');
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateCustomer(int customerId, String newFirstName, String newPurchaseType, String newPaymentMethod) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "UPDATE customer SET cust_firstname = ?, cust_purchasetype = ?, cust_paymentmethod = ? WHERE customer_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newFirstName);
                preparedStatement.setString(2, newPurchaseType);
                preparedStatement.setString(3, newPaymentMethod);
                preparedStatement.setInt(4, customerId);
                preparedStatement.executeUpdate();
                System.out.println("Customer updated in MySQL successfully!");
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteCustomer(int customerId) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "DELETE FROM customer WHERE customer_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, customerId);
                preparedStatement.executeUpdate();
                System.out.println("Customer deleted from MySQL successfully!");
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}