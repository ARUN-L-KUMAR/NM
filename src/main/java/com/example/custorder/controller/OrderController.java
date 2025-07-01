package com.example.custorder.controller;

import com.example.custorder.model.Order;
import com.example.custorder.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public Order save(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }


    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable(name = "id") long id) {
        Order order = orderService.getById(id);  // This will throw an exception if not found
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(name = "id") long id) {
        return orderService.deleteById(id);
    }

//    @GetMapping("/customer/{customerId}")
//    public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
//        return orderService.getOrdersByCustomerId(customerId);
//    }

}
