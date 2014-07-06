package org.kiwi.domain;

import org.apache.ibatis.annotations.Param;

public interface PaymentMapper {
    Payment getPayment(@Param("order") Order order);

    void createPayment(@Param("order") Order order, @Param("payment") Payment payment);
}
