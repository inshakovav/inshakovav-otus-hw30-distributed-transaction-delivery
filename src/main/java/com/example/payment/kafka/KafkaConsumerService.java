package com.example.payment.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final WarehouseService warehouseService;
    @KafkaListener(topics = "${delivery.kafka.payment-succeeded-topic}", groupId = "${delivery.kafka.message-group-name}")
    public void receivePaymentExecutedMessage(PaymentExecutedMessage message) {
        try {
            warehouseService.process(message);
        } catch (Exception e) {
            log.warn("Kafka unknown error Order processing: ", message);
        }

    }
}
