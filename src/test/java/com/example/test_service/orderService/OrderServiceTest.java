package com.example.test_service.orderService;

import com.example.test_service.orderService.model.Order;
import com.example.test_service.orderService.model.Product;
import com.example.test_service.orderService.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCreateOrder_Success() {
        List<Product> products = Arrays.asList(new Product(1L, "Product1", 500.0), new Product(2L, "Product2", 400.0));
        Order order = new Order();
        order.setProducts(products);

        Mockito.when(restTemplate.postForObject(Mockito.anyString(), Mockito.any(), Mockito.eq(String.class)))
                .thenReturn("SUCCESS");

        Order result = orderService.createOrder(order).getBody();
        assertNotNull(result);
        assertEquals("SUCCESS", result.getStatus());
    }

    @Test
    public void testCreateOrder_FailureDueToHighValue() {
        List<Product> products = Arrays.asList(new Product(1L, "Product1", 600.0), new Product(2L, "Product2", 500.0));
        Order order = new Order();
        order.setProducts(products);

        Order result = orderService.createOrder(order).getBody();
        assertEquals("FAILED", result.getStatus());
    }
}
