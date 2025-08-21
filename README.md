JAVA-MES-CONSUMER

Kafka 메시지를 소비(Consume) 하고, 처리 결과를 DB에 기록하는 컨슈머입니다.
외부 인터페이스는 현재 Mock 으로 대체되어 있습니다.

처리 흐름

Kafka 토픽에서 메시지 수신

큐 테이블(kafka_execution_queue)에 READY 상태 저장

Mock 인터페이스 실행 → 성공/실패 여부 결정

처리 결과 업데이트

SUCCESS

 RETRY_SUCCESS (재시도 중 성공)

FAIL (최대 3회 시도 후 실패)

메시지 예시
{
  "eventId": "SIG-202508-0001",
  "type": "SIGN_REQUEST",
  "payload": { "orderNo": "WO-123" },
  "timestamp": "2025-08-21T12:00:00Z"
}

 DB 테이블
CREATE TABLE KAFKA_EXECUTION_QUEUE (
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  EVENT_ID VARCHAR(100) UNIQUE,
  STATUS VARCHAR(20),     -- READY | SUCCESS | FAIL | RETRY_SUCCESS
  RETRY_COUNT INT DEFAULT 0,
  ERROR_MESSAGE TEXT,
  CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

 환경 변수
KAFKA_BOOTSTRAP_SERVERS=kafka:9092
KAFKA_TOPIC=mes.events
DB_URL=jdbc:mysql://mysql:3306/mes
DB_USER=root
DB_PASS=****
MOCK_MODE=always-success   # always-success | always-fail | random
