package com.kafka.fileRead.service;

import com.kafka.fileRead.model.DataRecord;
import com.kafka.fileRead.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    DataRepository dataRepository;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(DataRecord record) {
        try {
            log.info("Receive message: {}", record);
            dataRepository.save(record);
            log.info("Message successfully persisted to database");
        }catch (Exception e) {
            log.error("Error while saving message", e);
        }
    }
}
