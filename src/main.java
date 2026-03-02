import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create CRUD operation objects
        MySQLCustomerCRUD mySQLCRUD = new MySQLCustomerCRUD();
        MongoDBCustomerCRUD mongoCRUD = new MongoDBCustomerCRUD();

        System.out.println("=== RETAIL STORE CUSTOMER MANAGEMENT SYSTEM ===\n");

        // Display existing customer from MySQL
        System.out.println("--- EXISTING CUSTOMER IN MYSQL ---");
        Customer existingCustomer = mySQLCRUD.readCustomerById(12);
        if (existingCustomer != null) {
            System.out.println("Found existing customer: " + existingCustomer);
        }

        // Create three new Customer objects
        Customer customer1 = new Customer(101, "Alice", "Wonderland", "NY", 156.75);
        Customer customer2 = new Customer(102, "Bob", "Builder", "CA", 325.50);
        Customer customer3 = new Customer(103, "Charlie", "Chocolate", "IL", 89.99);

        // ==================== MYSQL OPERATIONS ====================
        System.out.println("\n=== MYSQL OPERATIONS ===");

        // CREATE - Insert customers into MySQL
        System.out.println("\n--- CREATING CUSTOMERS IN MYSQL ---");
        mySQLCRUD.createCustomer(customer1);
        mySQLCRUD.createCustomer(customer2);
        mySQLCRUD.createCustomer(customer3);

        // READ - Display all customers from MySQL
        System.out.println("\n--- READING ALL CUSTOMERS FROM MYSQL ---");
        List<Customer> mysqlCustomers = mySQLCRUD.readAllCustomers();
        for (Customer customer : mysqlCustomers) {
            System.out.println(customer);
        }

        // READ by ID - Get specific customer from MySQL
        System.out.println("\n--- READING CUSTOMER BY ID (101) FROM MYSQL ---");
        Customer foundCustomer = mySQLCRUD.readCustomerById(101);
        if (foundCustomer != null) {
            System.out.println("Found: " + foundCustomer);
        }

        // UPDATE - Modify a customer in MySQL
        System.out.println("\n--- UPDATING CUSTOMER IN MYSQL ---");
        customer1.setPurchaseTotal(199.99);
        customer1.setState("TX");
        mySQLCRUD.updateCustomer(customer1);

        // READ again to verify update
        System.out.println("\n--- VERIFYING UPDATE IN MYSQL ---");
        foundCustomer = mySQLCRUD.readCustomerById(101);
        if (foundCustomer != null) {
            System.out.println("Updated: " + foundCustomer);
        }

        // DELETE - Remove a customer from MySQL
        System.out.println("\n--- DELETING CUSTOMER FROM MYSQL ---");
        mySQLCRUD.deleteCustomer(102);

        // READ all to verify deletion
        System.out.println("\n--- ALL CUSTOMERS IN MYSQL AFTER DELETE ---");
        mysqlCustomers = mySQLCRUD.readAllCustomers();
        for (Customer customer : mysqlCustomers) {
            System.out.println(customer);
        }

        // ==================== MONGODB OPERATIONS ====================
        System.out.println("\n\n=== MONGODB OPERATIONS ===");

        // CREATE - Insert customers into MongoDB
        System.out.println("\n--- CREATING CUSTOMERS IN MONGODB ---");
        mongoCRUD.createCustomer(customer1);
        mongoCRUD.createCustomer(customer2);
        mongoCRUD.createCustomer(customer3);

        // READ - Display all customers from MongoDB
        System.out.println("\n--- READING ALL CUSTOMERS FROM MONGODB ---");
        List<Customer> mongoCustomers = mongoCRUD.readAllCustomers();
        for (Customer customer : mongoCustomers) {
            System.out.println(customer);
        }

        // READ by ID - Get specific customer from MongoDB
        System.out.println("\n--- READING CUSTOMER BY ID (101) FROM MONGODB ---");
        Customer foundMongoCustomer = mongoCRUD.readCustomerById(101);
        if (foundMongoCustomer != null) {
            System.out.println("Found: " + foundMongoCustomer);
        }

        // UPDATE - Modify a customer in MongoDB
        System.out.println("\n--- UPDATING CUSTOMER IN MONGODB ---");
        customer1.setPurchaseTotal(250.50);
        customer1.setLastName("Wonderland-Smith");
        mongoCRUD.updateCustomer(customer1);

        // READ again to verify update
        System.out.println("\n--- VERIFYING UPDATE IN MONGODB ---");
        foundMongoCustomer = mongoCRUD.readCustomerById(101);
        if (foundMongoCustomer != null) {
            System.out.println("Updated: " + foundMongoCustomer);
        }

        // DELETE - Remove a customer from MongoDB
        System.out.println("\n--- DELETING CUSTOMER FROM MONGODB ---");
        mongoCRUD.deleteCustomer(102);

        // READ all to verify deletion
        System.out.println("\n--- ALL CUSTOMERS IN MONGODB AFTER DELETE ---");
        mongoCustomers = mongoCRUD.readAllCustomers();
        for (Customer customer : mongoCustomers) {
            System.out.println(customer);
        }

        System.out.println("\n=== ALL OPERATIONS COMPLETED SUCCESSFULLY ===");
    }
}