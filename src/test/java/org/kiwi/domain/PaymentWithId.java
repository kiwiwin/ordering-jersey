package org.kiwi.domain;

public class PaymentWithId {
    public static Payment paymentWithId(int id, Payment payment) {
        payment.id = id;
        return payment;
    }
}
