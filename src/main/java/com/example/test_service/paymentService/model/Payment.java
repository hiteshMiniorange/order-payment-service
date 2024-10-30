package com.example.test_service.paymentService.model;

import com.example.test_service.orderService.model.Order;
import lombok.Data;

@Data
public class Payment {

    private Order order;
}
