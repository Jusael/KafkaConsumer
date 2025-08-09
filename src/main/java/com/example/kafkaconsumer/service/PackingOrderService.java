package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.dto.IfPackingOrderDto;

public interface PackingOrderService {

	void doSuccesInterface(IfPackingOrderDto dto, Long queId);
	
}
