package com.example.kafkaconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.kafkaconsumer.Listener.InterfaceListener;
import com.example.kafkaconsumer.dto.IfPackingOrderDto;
import com.example.kafkaconsumer.entity.KafkaExecutionQueue;
import com.example.kafkaconsumer.repository.KafkaExecutionQueueRepository;

@Service
public class PackingOrderServiceImpl implements PackingOrderService{

	private static final Logger log = LoggerFactory.getLogger(InterfaceListener.class);

	private enum Status {
		Ready, Fail, Success
	}

	private final KafkaExecutionQueueRepository kafkaExecutionQueueRepository;

	PackingOrderServiceImpl(KafkaExecutionQueueRepository kafkaExecutionQueueRepository) {
		this.kafkaExecutionQueueRepository = kafkaExecutionQueueRepository;
	}

	@Override
	@Transactional
	public void doSuccesInterface(IfPackingOrderDto dto, Long queId) {

		KafkaExecutionQueue kafkaExecutionQueue = kafkaExecutionQueueRepository.findById(queId)
				.orElseThrow(() -> new IllegalArgumentException("Not found QueInfo"));

		try {
			RestTemplate restTemp = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<IfPackingOrderDto> entity = new HttpEntity<>(dto, headers);
			ResponseEntity<String> response = restTemp.postForEntity("https://httpbin.org/post", entity, String.class);

			if (!response.getStatusCode().is2xxSuccessful())
				throw new RuntimeException("응답 실패: " + response.getStatusCode());

			kafkaExecutionQueue.setConsumedStatus(Status.Success.name());

		} catch (Exception e) {

			kafkaExecutionQueue.setConsumedStatus(Status.Fail.name());
			kafkaExecutionQueue.setErrorMsg(e.getMessage());

			log.error("인터페이스 실패", e);

		} finally {

			kafkaExecutionQueueRepository.save(kafkaExecutionQueue);

		}
	}
}