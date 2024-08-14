package com.order_grpc.order_service_grpc;


import com.order_grpc.order_service_grpc.entities.Order;
import com.order_grpc.order_service_grpc.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(OrderRepository orderRepository) {
        return args -> {
            Order order1 = new Order();
            order1.setInvoiceId("inv-001");
            order1.setCustomerId("cust-001");
            order1.setCreatedTime(LocalDateTime.of(2024, 8, 14, 10, 0));

            Order order2 = new Order();
            order2.setInvoiceId("inv-002");
            order2.setCustomerId("cust-002");
            order2.setCreatedTime(LocalDateTime.of(2024, 8, 14, 11, 0));

            Order order3 = new Order();
            order3.setInvoiceId("inv-003");
            order3.setCustomerId("cust-003");
            order3.setCreatedTime(LocalDateTime.of(2024, 8, 14, 12, 0));

            orderRepository.save(order1);
            orderRepository.save(order2);
            orderRepository.save(order3);

            System.out.println("Sample data inserted into the orders table");
        };
    }
}
