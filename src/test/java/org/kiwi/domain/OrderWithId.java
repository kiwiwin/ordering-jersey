package org.kiwi.domain;

public class OrderWithId {
    public static Order orderWithId(int id, Order order) {
        order.id = id;
        return order;
    }
}
