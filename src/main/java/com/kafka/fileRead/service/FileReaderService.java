package com.kafka.fileRead.service;

import com.kafka.fileRead.model.DataRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class FileReaderService {

    @Autowired
    private ObjectMapper objectMapper;

    public List<DataRecord> readJsonFile(String filePath) throws IOException {
        return Arrays.asList(
                objectMapper.readValue(new File(filePath), DataRecord[].class)
        );

    }

}
