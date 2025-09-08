package com.ach.order_serv.service;

import com.ach.order_serv.dao.OrderRepository;
import com.ach.order_serv.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "order-topic";

    public Order placeOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        // Publish order event as JSON
        try {
            ObjectMapper mapper = new ObjectMapper();
            String orderJson = mapper.writeValueAsString(savedOrder);
            kafkaTemplate.send(TOPIC, orderJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}