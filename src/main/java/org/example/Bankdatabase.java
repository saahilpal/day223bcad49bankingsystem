package org.example;

import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Bankdatabase {
    private final MongoCollection<Document> bank;

    public Bankdatabase() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = client.getDatabase("testdb");
        bank = db.getCollection("Bank");
    }

    public void createAccount(String id, String name) {
        if (bank.find(eq("accountId", id)).first() != null) {
            System.out.println("Account already exists.");
            return;
        }

        Document doc = new Document("accountId", id)
                .append("name", name)
                .append("balance", 0.0);
        bank.insertOne(doc);
        System.out.println("Account created.");
    }

    public void deposit(String id, double amount) {
        Document doc = bank.find(eq("accountId", id)).first();
        if (doc == null) {
            System.out.println("Account not found.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Deposit must be more than 0.");
            return;
        }

        double balance = doc.getDouble("balance") + amount;
        bank.updateOne(eq("accountId", id), new Document("$set", new Document("balance", balance)));
        System.out.println("₹" + amount + " deposited.");
    }

    public void withdraw(String id, double amount) {
        Document doc = bank.find(eq("accountId", id)).first();
        if (doc == null) {
            System.out.println("Account not found.");
            return;
        }

        double balance = doc.getDouble("balance");
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Not enough balance.");
            return;
        }

        bank.updateOne(eq("accountId", id), new Document("$set", new Document("balance", balance - amount)));
        System.out.println("₹" + amount + " withdrawn.");
    }

    public void checkBalance(String id) {
        Document doc = bank.find(eq("accountId", id)).first();
        if (doc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Account Holder: " + doc.getString("name"));
        System.out.println("Current Balance: ₹" + doc.getDouble("balance"));
    }
}