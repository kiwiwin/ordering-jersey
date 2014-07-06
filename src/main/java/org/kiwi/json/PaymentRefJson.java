package org.kiwi.json;

import org.kiwi.domain.Payment;

public class PaymentRefJson {
    private final Payment payment;

    public PaymentRefJson(Payment payment) {
        this.payment = payment;
    }

    public String getType() {
        return payment.getType();
    }
}
