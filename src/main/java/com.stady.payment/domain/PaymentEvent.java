package com.stady.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentEvent(Integer id, LocalDateTime eventDateTime, PaymentStatus paymentStatus, BigDecimal amount) {
    public PaymentEvent(Integer id, LocalDateTime eventDateTime, PaymentStatus paymentStatus, BigDecimal amount) {
        this.id = id;
        this.eventDateTime = eventDateTime;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
    }

    public Integer id() {
        return this.id;
    }

    public LocalDateTime eventDateTime() {
        return this.eventDateTime;
    }

    public PaymentStatus paymentStatus() {
        return this.paymentStatus;
    }

    public BigDecimal amount() {
        return this.amount;
    }
}
