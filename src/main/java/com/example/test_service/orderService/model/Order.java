package com.example.test_service.orderService.model;

import lombok.Data;

import java.util.List;

@Data
public class Order {

    private Long orderId;
    private String customerId;
    private List<Product> products;
    private String status;   // "SUCCESS" or "FAILED"
    private Double value;



}
