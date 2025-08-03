package com.example.kafkaconsumer.service;

@Service
public class PackingOrderService {
    public void process(PackingOrderDto dto) {
        System.out.println("📦 PackingOrder 처리: " + dto.getPackingNo());
    }
}