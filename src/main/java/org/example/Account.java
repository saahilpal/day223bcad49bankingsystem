package org.example;

public class Account {
    private String id;
    private String name;
    private Double  balance;

    public Account(String accountId, String holderName, Double balance)
    {
        this.id =accountId;
        this.name =holderName;
        this.balance=balance;
    }

    public Double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
