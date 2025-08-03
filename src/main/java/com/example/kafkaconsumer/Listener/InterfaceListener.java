
package com.example.kafkaconsumer.Listener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InterfaceListener {

    private final ObjectMapper objectMapper;
    private final WorkOrderService workOrderService;
    private final PackingOrderService packingOrderService;

    @KafkaListener(topics = "order-topic", groupId = "order-consumer-group")
    public void listen(ConsumerRecord<String, String> record) throws Exception {
        String key = record.key();
        String value = record.value();

        switch (key) {
            case "work_order":
                WorkOrderDto workOrder = objectMapper.readValue(value, WorkOrderDto.class);
                workOrderService.process(workOrder);
                break;

            case "packing_order":
                PackingOrderDto packingOrder = objectMapper.readValue(value, PackingOrderDto.class);
                packingOrderService.process(packingOrder);
                break;

            default:
                log.warn("Unknown key: {}", key);
        }
    }
    
}
