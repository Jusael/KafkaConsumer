JAVA-MES-CONSUMER

Kafka 메시지를 소비하고, 처리 결과를 DB에 기록하는 컨슈머입니다.
외부 인터페이스는 현재 Mock 으로 대체되어 있습니다.

- 메시지 수신 → Kafka 토픽
- 큐 테이블 기록 → READY 상태
- Mock 인터페이스 실행 → 성공/실패 여부 결정
 결과 업데이트
    - SUCCESS : 정상 처리
    - RETRY_SUCCESS : 재시도 후 성공

메시지 예시
{
  "eventId": "SIG-202508-0001",
  "type": "SIGN_REQUEST",
  "payload": { "orderNo": "WO-123" },
  "timestamp": "2025-08-21T12:00:00Z"
}
