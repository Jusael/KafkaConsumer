package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.dto.IfOrderDto;

public interface WorkOrderService {
	 void doFailInterface(IfOrderDto dto , Long queId);
}
