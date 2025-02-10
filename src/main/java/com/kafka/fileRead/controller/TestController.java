package com.kafka.fileRead.controller;

import com.kafka.fileRead.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final KafkaProducerService producerService;
    private final ResourceLoader resourceLoader;

    @PostMapping("/process-json")
    public ResponseEntity<?> processJsonFile() {
        try {
            String path = "D:/BJB/ROC_BJB/kafka/fileRead/src/main/resources/data.json";
            producerService.processJsonFileAndSend(path);
            return ResponseEntity.ok("JSON file processing initiated");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error processing JSON file: " + e.getMessage());
        }
    }
}
