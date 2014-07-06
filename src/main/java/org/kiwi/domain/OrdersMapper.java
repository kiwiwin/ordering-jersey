package org.kiwi.domain;

public interface OrdersMapper {
    Order getOrder(User user, int orderId);

    int createOrder(Order order);
}
