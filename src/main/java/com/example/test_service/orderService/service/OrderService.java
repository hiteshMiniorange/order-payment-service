package com.example.test_service.orderService.service;

import com.example.test_service.orderService.model.Order;
import com.example.test_service.orderService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Order> createOrder(Order order) {

        double totalValue = order.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();

        order.setValue(totalValue);

        String paymentServiceUrl = "http:localhost:8080/v1/payment/process"; //We can consider this is another service url (payment-service)
        String paymentStatus = restTemplate.postForObject(paymentServiceUrl, totalValue, String.class);
        order.setStatus(paymentStatus);

        return ResponseEntity.ok(order);
    }
}
