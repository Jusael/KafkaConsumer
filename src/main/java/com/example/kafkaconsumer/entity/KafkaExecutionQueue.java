package com.example.kafkaconsumer.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "KAFKA_EXECUTION_QUEUE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaExecutionQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUE_ID")
    private Long queId;

    @Column(name = "TOPIC", nullable = false)
    private String topic;

    @Column(name = "PAYLOAD", nullable = false, columnDefinition = "TEXT")
    private String payload;

    @Column(name = "STATUS", length = 20)
    private String status; // READY / SUCCESS / FAIL / RETRY_SUCCESS

    @Column(name = "ERROR_MSG", columnDefinition = "TEXT")
    private String errorMsg;

    @Column(name = "CNT")
    private Integer cnt;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "SP_QUE_ID")
    private Long spQueId;

    @Column(name = "CONSUMED_STATUS", length = 20)
    private String consumedStatus; // SUCCESS / FAIL

    @Column(name = "CONSUMED_ERROR_MSG", columnDefinition = "TEXT")
    private String consumedErrorMsg;

    @Column(name = "CONSUMED_AT")
    private LocalDateTime consumedAt;

}
