
package com.example.kafkaconsumer.Listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.kafkaconsumer.dto.IfOrderDto;
import com.example.kafkaconsumer.dto.IfPackingOrderDto;
import com.example.kafkaconsumer.service.PackingOrderService;
import com.example.kafkaconsumer.service.WorkOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InterfaceListener {

	private static final Logger log = LoggerFactory.getLogger(InterfaceListener.class);

	private final ObjectMapper objectMapper;
	private final WorkOrderService workOrderService;
	private final PackingOrderService packingOrderService;

	@KafkaListener(topics = "order-approve-topic", groupId = "order-consumer-group")
	public void listenOrder(ConsumerRecord<String, String> record) throws Exception {
		Long queId = Long.parseLong(record.key());
		String value = record.value();

		IfOrderDto ifOrderDto = objectMapper.readValue(value, IfOrderDto.class);
		workOrderService.doFailInterface(ifOrderDto, queId);
	}

	@KafkaListener(topics = "packing-approve-topic", groupId = "order-consumer-group")
	public void listenIfPackingOrder(ConsumerRecord<String, String> record) throws Exception {

		Long queId = Long.parseLong(record.key());
		String value = record.value();
		IfPackingOrderDto ifPackingOrderDto = objectMapper.readValue(value, IfPackingOrderDto.class);
		packingOrderService.doSuccesInterface(ifPackingOrderDto, queId);
	}

}
