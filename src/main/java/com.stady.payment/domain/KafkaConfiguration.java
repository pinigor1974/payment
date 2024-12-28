package com.stady.payment.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.ssl.NoSuchSslBundleException;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@ConfigurationProperties(
        prefix = "app"
)
@RequiredArgsConstructor
@Data
public class KafkaConfiguration {
    private final KafkaProperties kafkaProperties;
    private String sendingTopicName;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>(this.kafkaProperties.buildProducerProperties(new SslBundles() {
            public SslBundle getBundle(String name) throws NoSuchSslBundleException {
                return null;
            }

            public void addBundleUpdateHandler(String name, Consumer<SslBundle> updateHandler) throws NoSuchSslBundleException {
            }

            public List<String> getBundleNames() {
                return List.of();
            }
        }));
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, PaymentEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<String, PaymentEvent>(this.producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, PaymentEvent> kafkaTemplate() {
        return new KafkaTemplate<String, PaymentEvent>(this.producerFactory());
    }





}
