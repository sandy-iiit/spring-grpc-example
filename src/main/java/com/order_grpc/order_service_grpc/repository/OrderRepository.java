package com.order_grpc.order_service_grpc.repository;

import com.order_grpc.order_service_grpc.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllById(Iterable<String> ids);

}
