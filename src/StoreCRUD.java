import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class MongoDBCustomerCRUD {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "store";
    private static final String COLLECTION_NAME = "customers";

    // Create (Insert) operation
    public void createCustomer(Customer customer) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document customerDoc = new Document("customer_id", customer.getCustomerId())
                    .append("cust_firstname", customer.getFirstName())
                    .append("cust_lastname", customer.getLastName())
                    .append("cust_state", customer.getState())
                    .append("cust_purchasetotal", customer.getPurchaseTotal());

            collection.insertOne(customerDoc);
            System.out.println("Customer inserted into MongoDB successfully!");
        }
    }

    // Read all customers operation
    public List<Customer> readAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            FindIterable<Document> customerDocs = collection.find();

            for (Document doc : customerDocs) {
                Customer customer = documentToCustomer(doc);
                customers.add(customer);
            }
        }

        return customers;
    }

    // Read by ID operation
    public Customer readCustomerById(int customerId) {
        Customer customer = null;

        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = Filters.eq("customer_id", customerId);
            Document doc = collection.find(filter).first();

            if (doc != null) {
                customer = documentToCustomer(doc);
            }
        }

        return customer;
    }

    // Update operation
    public void updateCustomer(Customer customer) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = Filters.eq("customer_id", customer.getCustomerId());

            Document updateDoc = new Document("$set", new Document()
                    .append("cust_firstname", customer.getFirstName())
                    .append("cust_lastname", customer.getLastName())
                    .append("cust_state", customer.getState())
                    .append("cust_purchasetotal", customer.getPurchaseTotal()));

            collection.updateOne(filter, updateDoc);
            System.out.println("Customer updated in MongoDB successfully!");
        }
    }

    // Delete operation
    public void deleteCustomer(int customerId) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = Filters.eq("customer_id", customerId);
            collection.deleteOne(filter);
            System.out.println("Customer deleted from MongoDB successfully!");
        }
    }

    // Helper method to convert Document to Customer
    private Customer documentToCustomer(Document doc) {
        return new Customer(
                doc.getInteger("customer_id"),
                doc.getString("cust_firstname"),
                doc.getString("cust_lastname"),
                doc.getString("cust_state"),
                doc.getDouble("cust_purchasetotal")
        );
    }
}