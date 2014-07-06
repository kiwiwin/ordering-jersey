package org.kiwi.domain;

public interface PaymentMapper {
    Payment getPayment(Order order);

    void createPayment(Order order, Payment payment);
}
