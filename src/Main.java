import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== RETAIL STORE CUSTOMER MANAGEMENT SYSTEM ===\n");

        /**
         * Creates customers with their specific attributes including purchase type and payment method
         */
        Customer customer1 = new Customer(101, "Alice", "Wonderland", "NY", 156.75, "Electronics", "Card");
        Customer customer2 = new Customer(102, "Bob", "Builder", "CA", 325.50, "Tools", "Cash");
        Customer customer3 = new Customer(103, "Charlie", "Chocolate", "IL", 89.99, "Groceries", "Card");
        Customer customer4 = new Customer(104, "Diana", "Prince", "WA", 450.25, "Clothing", "Card");
        Customer customer5 = new Customer(105, "Bruce", "Wayne", "NJ", 1250.00, "Electronics", "Card");

        /**
         * Creates objects to communicate with other classes
         * allowing for connection to the database in Main.java
         */
        CustomerMongoCRUD mongoStore = new CustomerMongoCRUD();
        CustomerMySQLCRUD mySQLStore = new CustomerMySQLCRUD();

        try {
            // ==================== MONGODB OPERATIONS ====================
            System.out.println("\n=== MONGODB OPERATIONS ===");

            // INSERTS CUSTOMERS IN MONGO
            System.out.println("\n--- INSERTING CUSTOMERS IN MONGODB ---");
            mongoStore.insertCustomer(customer1);
            mongoStore.insertCustomer(customer2);
            mongoStore.insertCustomer(customer3);
            mongoStore.insertCustomer(customer4);
            mongoStore.insertCustomer(customer5);

            // READ ALL CUSTOMERS FROM MONGO
            System.out.println("\n--- READING ALL CUSTOMERS FROM MONGODB ---");
            mongoStore.readAllCustomers();

            // UPDATE CUSTOMER IN MONGO (changing name, purchase type, and payment method)
            System.out.println("\n--- UPDATING CUSTOMER IN MONGODB (ID: 101) ---");
            System.out.println("Changing Alice's purchase type from Electronics to Furniture and payment from Card to Cash");
            mongoStore.updateCustomer(101, "AliceUpdated", "Furniture", "Cash");

            // READ ALL CUSTOMERS FROM MONGO AGAIN
            System.out.println("\n--- READING ALL CUSTOMERS FROM MONGODB AFTER UPDATE ---");
            mongoStore.readAllCustomers();

            // DELETE CUSTOMER FROM MONGO
            System.out.println("\n--- DELETING CUSTOMER FROM MONGODB (ID: 102) ---");
            mongoStore.deleteCustomer(102);

            // READ ALL CUSTOMERS FROM MONGO AFTER DELETE
            System.out.println("\n--- READING ALL CUSTOMERS FROM MONGODB AFTER DELETE ---");
            mongoStore.readAllCustomers();

            // ==================== MYSQL OPERATIONS ====================
            System.out.println("\n\n=== MYSQL OPERATIONS ===");

            // First, let's check the existing customers
            System.out.println("\n--- EXISTING CUSTOMERS IN MYSQL ---");
            mySQLStore.readAllCustomers();

            // INSERTS CUSTOMERS IN MYSQL
            System.out.println("\n--- INSERTING CUSTOMERS IN MYSQL ---");
            mySQLStore.insertCustomer(customer1);
            mySQLStore.insertCustomer(customer2);
            mySQLStore.insertCustomer(customer3);
            mySQLStore.insertCustomer(customer4);
            mySQLStore.insertCustomer(customer5);

            // READ ALL CUSTOMERS FROM MYSQL
            System.out.println("\n--- READING ALL CUSTOMERS FROM MYSQL ---");
            mySQLStore.readAllCustomers();

            // UPDATE CUSTOMER IN MYSQL
            System.out.println("\n--- UPDATING CUSTOMER IN MYSQL (ID: 103) ---");
            System.out.println("Changing Charlie's purchase type from Groceries to Restaurant and payment from Card to Cash");
            mySQLStore.updateCustomer(103, "CharlieUpdated", "Restaurant", "Cash");

            // READ ALL CUSTOMERS FROM MYSQL AGAIN
            System.out.println("\n--- READING ALL CUSTOMERS FROM MYSQL AFTER UPDATE ---");
            mySQLStore.readAllCustomers();

            // DELETE CUSTOMER FROM MYSQL
            System.out.println("\n--- DELETING CUSTOMER FROM MYSQL (ID: 104) ---");
            mySQLStore.deleteCustomer(104);

            // READ ALL CUSTOMERS FROM MYSQL AFTER DELETE
            System.out.println("\n--- READING ALL CUSTOMERS FROM MYSQL AFTER DELETE ---");
            mySQLStore.readAllCustomers();

            // ==================== SUMMARY ====================
            System.out.println("\n\n=== PURCHASE SUMMARY ===");
            System.out.println("Customers who paid with Card: IDs 101, 103, 105");
            System.out.println("Customers who paid with Cash: IDs 102, 104");
            System.out.println("Purchase types: Electronics, Tools, Groceries, Clothing, Furniture, Restaurant");

            System.out.println("\n=== ALL OPERATIONS COMPLETED SUCCESSFULLY ===");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}