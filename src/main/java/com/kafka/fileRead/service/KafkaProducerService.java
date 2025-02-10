package com.kafka.fileRead.service;

import com.kafka.fileRead.model.DataRecord;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    @Value("${kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, DataRecord> kafkaTemplate;

    @Autowired
    private FileReaderService fileReaderService;

    public void processJsonFileAndSend(String filePath) {
        try {
            List<DataRecord> records = fileReaderService.readJsonFile(filePath);
            for (DataRecord record : records) {
                kafkaTemplate.send(topicName, record).toCompletableFuture()
                        .thenAccept(
                                result -> log.info("Message sent successfully to topic {}",result.getRecordMetadata().topic()))
                        .exceptionally(ex -> {
                            log.error("Error while sending message to topic {}",topicName,ex);
                            return null;
                        });
            }
        }catch (Exception e) {
            log.error("Error while sending message", e);
            throw new RuntimeException("Error processing JSON file",e);
        }
    }
}
