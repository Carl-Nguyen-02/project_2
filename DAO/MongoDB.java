package DAO;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import model.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class MongoDB{
    private MongoClient mongoClient;
    private MongoDatabase database;
    public MongoDB() {
        this.mongoClient = MongoClients.create("mongodb+srv://chamber2002:8dSRWJchmMOL31YL@project2.jvpnw.mongodb.net/");
        this.database = mongoClient.getDatabase("project_2");
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }

    public Customer getCustomerByID(String customerId) {
        MongoCollection<Document> collection = database.getCollection("customers");
        Document doc = collection.find(Filters.eq("id", customerId)).first();
        if (doc != null) {
            Customer customer = new Customer(
                    doc.getString("fullName"),
                    doc.getString("id")
            );
//            customer.setCart(deserializeCart((List<Document>) doc.get("cart")));
            return customer;
        }
        return null;
    }

    public void saveTransaction(Transaction transaction) {
        MongoCollection<Document> ordersCollection = database.getCollection("Transactions");

        Document orderDoc = new Document("orderID", transaction.getOrderID()) // Save order ID
                .append("sourceType", transaction.getSourceType())
                .append("sourceID", transaction.getSourceID())
                .append("orderDate", transaction.getOrderDate())
                .append("totalCost", transaction.getTotalCost())
                .append("paymentMethod", transaction.getPaymentMethod())
                .append("orderLines", serializeOrderLines(transaction.getLines()));

        if (transaction.getCustomerID() != null && !transaction.getCustomerID().isEmpty()) {
            orderDoc.append("customerID", transaction.getCustomerID());
        }

        ordersCollection.insertOne(orderDoc);
    }

    private List<Document> serializeOrderLines(List<TransactionInfo> lines) {
        List<Document> orderLines = new ArrayList<>();
        for (TransactionInfo line : lines) {
            orderLines.add(new Document("productID", line.getProductID())
                    .append("productName", line.getProductName())
                    .append("price", line.getPrice())
                    .append("quantity", line.getQuantity())
                    .append("cost", line.getCost()));
        }
        return orderLines;
    }

    //Return Transaction History for Cashiers' accounts
    public MongoCollection<Document>  getCashierHistory(User cashier) {
        Bson filter = Filters.and(
                Filters.eq("sourceType", "Transaction"),
                Filters.eq("sourceID", String.valueOf(cashier.getUserID()))
        );

        // Filter is applied later, so return the collection
        return database.getCollection("Transactions");
    }

}
