package org.kiwi.domain;

import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    Order getOrder(@Param("orderId") int orderId);

    void createOrder(@Param("user") User user, @Param("order") Order order);
}
