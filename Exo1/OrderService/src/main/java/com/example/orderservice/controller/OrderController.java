package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDto> create (@RequestBody Order order){
        return ResponseEntity.ok(orderService.create(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable int id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/product/{product}")
    public ResponseEntity<List<OrderResponseDto>> findByProduct(@PathVariable String product){
        return ResponseEntity.ok(orderService.findByProduct(product));
    }
}
