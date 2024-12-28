package com.stady.payment.adapter.in.controller;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

import com.stady.payment.domain.KafkaConfiguration;
import com.stady.payment.domain.PaymentEvent;
import com.stady.payment.domain.PaymentStatus;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class SendPaymentsController {

    private static final Logger log = LoggerFactory.getLogger(SendPaymentsController.class);
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private final KafkaConfiguration kafkaConfiguration;

    @GetMapping({"sendPayment"})
    public ResponseEntity<String> send(@RequestParam Integer number) throws InterruptedException {
        Random rnd = new Random(100000L);
        int orderId = rnd.nextInt();
        BigDecimal amountId = BigDecimal.valueOf((long)(new Random(100000L)).nextInt());

        for(int i = 0; i < number; ++i) {
            Thread.sleep(10L);
            Arrays.stream(PaymentStatus.values()).forEach((v) -> {
                this.kafkaTemplate.send(this.kafkaConfiguration.getSendingTopicName(), new PaymentEvent(orderId, LocalDateTime.now(), v, amountId));
            });
        }

        return new ResponseEntity<String>("Generated", HttpStatus.OK);
    }

    @Generated
    public SendPaymentsController(final KafkaTemplate<String, PaymentEvent> kafkaTemplate, final KafkaConfiguration kafkaConfiguration) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfiguration = kafkaConfiguration;
    }
}
