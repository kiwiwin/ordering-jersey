package org.kiwi.domain;

public class Product {
    int id;
    private String name;
    private String description;
    private int price;

    private Product() {

    }

    public Product(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() { return price; }
}
