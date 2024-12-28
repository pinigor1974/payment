package com.stady.payment;


import com.stady.payment.domain.KafkaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties({KafkaConfiguration.class})
public class PaymentGenerator {
    public PaymentGenerator() {
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(PaymentGenerator.class, args);
    }
}
