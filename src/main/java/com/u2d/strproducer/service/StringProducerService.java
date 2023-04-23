package com.u2d.strproducer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Slf4j
@Service
public class StringProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) throws ExecutionException, InterruptedException {
        var response = kafkaTemplate.send("str-topic", message);
        var metadata = response.get().getRecordMetadata();
        log.info("Partition: {} offset: {}", metadata.partition(), metadata.offset());
    }
}
