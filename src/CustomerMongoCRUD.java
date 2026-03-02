/** Project: Solo Lab3 Database
 * Purpose Details: code for Mongo crud
 * Course: IST 242
 * Author: Nafiz Hussain
 * Date Developed: 2/22/2026
 * Last Date Changed: 3/1/2026
 * Rev: 3

 */


import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

public class CustomerMongoCRUD {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "storedatabase";
    private static final String COLLECTION_NAME = "store";

    public void insertCustomer(Customer customer) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document newCustomer = new Document("customer_id", customer.getCustomerId())
                    .append("cust_firstname", customer.getFirstName())
                    .append("cust_lastname", customer.getLastName())
                    .append("cust_state", customer.getState())
                    .append("cust_purchasetotal", customer.getPurchaseTotal())
                    .append("cust_purchasetype", customer.getPurchaseType())
                    .append("cust_paymentmethod", customer.getPaymentMethod());

            collection.insertOne(newCustomer);
            System.out.println("Customer inserted into MongoDB successfully!");
        }
    }

    public void readAllCustomers() {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            FindIterable<Document> customers = collection.find();
            for (Document customer : customers) {
                System.out.println(customer.toJson());
            }
        }
    }

    public void updateCustomer(int customerId, String newFirstName, String newPurchaseType, String newPaymentMethod) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document updatedCustomer = new Document("$set", new Document()
                    .append("cust_firstname", newFirstName)
                    .append("cust_purchasetype", newPurchaseType)
                    .append("cust_paymentmethod", newPaymentMethod));

            collection.updateOne(new Document("customer_id", customerId), updatedCustomer);
            System.out.println("Customer updated in MongoDB successfully!");
        }
    }

    public void deleteCustomer(int customerId) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.deleteOne(new Document("customer_id", customerId));
            System.out.println("Customer deleted from MongoDB successfully!");
        }
    }
}