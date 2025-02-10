package com.kafka.fileRead.repository;

import com.kafka.fileRead.model.DataRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<DataRecord, Long> {

}
