package org.kiwi.domain;

public class Payment {
    int id;
    private String type;
    private int amount;

    public Payment(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}
