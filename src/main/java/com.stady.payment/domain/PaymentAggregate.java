package com.stady.payment.domain;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Generated;

record PaymentAggregate (
    Long Id,
    LocalDateTime createdAd,
    LocalDateTime updatedAt,
    Set<PaymentStatus> statuses
)
{

}

