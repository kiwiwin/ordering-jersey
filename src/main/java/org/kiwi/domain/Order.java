package org.kiwi.domain;

public class Order {
    int id;
    private Product product;

    private Order() {

    }

    public Order(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }
}
