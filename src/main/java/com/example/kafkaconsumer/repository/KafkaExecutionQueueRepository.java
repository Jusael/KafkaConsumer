package com.example.kafkaconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kafkaconsumer.entity.KafkaExecutionQueue;

@Repository
public interface KafkaExecutionQueueRepository extends JpaRepository<KafkaExecutionQueue, Long> {

}
