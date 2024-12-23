package DAO;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import model.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class MongoDB{
    private MongoClient mongoClient;
    private MongoDatabase database;
    public MongoDB() {
        this.mongoClient = MongoClients.create("mongodb+srv://chamber2002:8dSRWJchmMOL31YL@project2.jvpnw.mongodb.net/");
        this.database = mongoClient.getDatabase("customer_info");
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }

    public Customer getCustomerById(String customerId) {
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


}
