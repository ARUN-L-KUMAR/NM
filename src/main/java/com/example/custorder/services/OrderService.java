package com.example.custorder.services;

import com.example.custorder.Exception.ResourceNotFoundException;
import com.example.custorder.model.Order;
import com.example.custorder.repo.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepo repo;

    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public Order saveOrder(Order order) {
         return repo.save(order);
    }


    public List<Order> getOrders() {
        return repo.findAll();
    }

    public List<Order> getAll() {
        return repo.findAll();
    }

    public Order getById(long id) {
        return repo.findById((int) id).orElseThrow(() ->
                new ResourceNotFoundException("Order not found with ID: " + id));
    }


    public String deleteById(long id) {
        repo.deleteById((int) id);
        return "Deleted";
    }

//    public List<Order> getOrdersByCustomerId(Long customerId) {
//        return repo.findByCustomerId(customerId);
//    }
}
