JAVA-MES-CONSUMER

Kafka 메시지를 소비하고 비즈니스 로직을 실행한 뒤, 처리 성공/실패 상태를 DB에 기록합니다. 외부 인터페이스 연동은 현재 mock으로 대체합니다.

핵심 요약

역할: Kafka 메시지 소비 → 처리 → kafka_execution_queue 업데이트

상태값: READY, SUCCESS, FAIL, RETRY_SUCCESS

재시도: 최대 3회(지수 백오프 권장)

아이템포턴시: 키 기준 중복처리 방지(큐 테이블/유니크 인덱스 활용)

인터페이스: 실제 연동 전까지 mock 서비스로 대체

아키텍처 개요
[KAFKA] --> [Consumer] --> [Service] --(처리결과)--> [MySQL.kafka_execution_queue]
                             \
                              +--> [MockInterfaceAdapter]


Consumer: 특정 토픽 구독, 메시지 역직렬화 → 서비스 호출

Service: 도메인 처리, 트랜잭션 관리, 상태 업데이트

MockInterfaceAdapter: 외부 시스템 연동부를 임시로 대체(성공/실패 시나리오 가변)

선행 조건

Kafka(내부 네트워크): bootstrap.servers=kafka:9092

MySQL: 접속 가능 계정/스키마

JDK 17, Spring Boot 3.0

(운영 배포) Docker / Docker Compose, GitHub Actions 사용 가능


메시지 스키마(예시)
{
  "eventId": "SIG-202508-000123",
  "type": "SIGN_REQUEST",
  "payload": {
    "orderNo": "WO-2025-0815-001",
    "itemCd": "P-ABC-001",
    "lotNo": "L20250815-01",
    "expiresAt": "2025-12-31"
  },
  "timestamp": "2025-08-15T12:34:56Z"
}

