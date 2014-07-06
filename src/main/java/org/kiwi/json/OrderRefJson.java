package org.kiwi.json;

import org.kiwi.domain.Order;

public class OrderRefJson {
    private final Order order;

    public OrderRefJson(Order order) {
        this.order = order;
    }

    public int getPrice() {
        return order.getProduct().getPrice();
    }
}
