package com.order_grpc.order_service_grpc.service;

import com.order_grpc.order_service_grpc.dtos.OrderOuterClass;
import com.order_grpc.order_service_grpc.dtos.OrderRequestOuterClass.OrderRequest;
import com.order_grpc.order_service_grpc.dtos.OrderResponseOuterClass.OrderResponse;
import com.order_grpc.order_service_grpc.entities.Order;
import com.order_grpc.order_service_grpc.repository.OrderRepository;
import io.grpc.stub.StreamObserver;
import lombok.*;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;


@GrpcService
    @RequiredArgsConstructor
    public class OrderService extends OrderServiceGrpc.OrderServiceImplBase {

        private final OrderRepository orderRepo;

        @Override
        public void getOrders(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
            List<String> orderIds = request.getOrderIdsList();
            List<Order> orders = orderRepo.findAllById(orderIds);
            System.out.println(orders.size());

            responseObserver.onNext(prepareOrderResponse(orders));
            responseObserver.onCompleted();
        }

        private OrderResponse prepareOrderResponse(List<Order> orders) {
            List<OrderOuterClass.Order> ordersResponseList =
                    orders.stream()
                            .map(this::getOrder)
                            .collect(Collectors.toList());
            return OrderResponse.newBuilder().addAllOrders(ordersResponseList).build();
        }

        private OrderOuterClass.Order getOrder(Order order) {
            OrderOuterClass.Order.Builder order1 = OrderOuterClass.Order.newBuilder();

            order1.setOrderId(String.valueOf(order.getOrderId()));
            order1.setCustomerId(order.getCustomerId());
            order1.setInvoiceId(order.getInvoiceId());
            order1.setCreatedTime(String.valueOf(order.getCreatedTime()));

            return order1.build();
        }
    }

