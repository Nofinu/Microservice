package com.example.orderservice.service;

import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.dto.User;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.tools.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final RestClient<User> restClient;

    public OrderService() {
        this.restClient = new RestClient<>();
    }

    public OrderResponseDto create (Order order){
        Order orderCreate = orderRepository.save(order);
        User user = restClient.get("user/"+order.getUserId(), User.class);
        return OrderResponseDto.builder().id(orderCreate.getId()).user(user).product(orderCreate.getProduct()).build();

    }

    public OrderResponseDto findById(int id){
        Optional<Order> orderOptional = orderRepository.findById(id);
        if(orderOptional.isPresent()){
            User user = restClient.get("user/"+orderOptional.get().getUserId(), User.class);
            return OrderResponseDto.builder().id(orderOptional.get().getId()).user(user).product(orderOptional.get().getProduct()).build();
        }
        return null;
    }

    public List<OrderResponseDto> findByProduct (String product){
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        List<Order> orders = orderRepository.findByProduct(product);

        orders.forEach(o -> {
            User user = restClient.get("user/"+o.getUserId(), User.class);
            orderResponseDtos.add(OrderResponseDto.builder().id(o.getId()).user(user).product(o.getProduct()).build()) ;
        });
        return orderResponseDtos;
    }
}
