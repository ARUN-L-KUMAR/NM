package com.example.ecommerce.service;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderStatus;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                       CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }
    
    // Create a new order
    public Order createOrder(Long customerId, String shippingAddress, BigDecimal totalAmount) {
        Customer customer = customerService.getCustomerById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        Order order = new Order(customer);
        order.setShippingAddress(shippingAddress);
        order.setTotalAmount(totalAmount != null ? totalAmount : BigDecimal.ZERO);
        return orderRepository.save(order);
    }
    
    // Get all orders
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    // Get order by ID
    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    
    // Get orders by customer ID
    @Transactional(readOnly = true)
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerIdOrderByOrderDateDesc(customerId);
    }
    
    // Get orders by status
    @Transactional(readOnly = true)
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatusOrderByOrderDateDesc(status);
    }
    
    // Update order status
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        
        order.setStatus(status);
        return orderRepository.save(order);
    }
    
    // Update order total amount
    public Order updateOrderTotal(Long orderId, BigDecimal totalAmount) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }
    
    // Cancel order
    public Order cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new IllegalArgumentException("Cannot cancel a delivered order");
        }

        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
    
    // Delete order
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        orderRepository.delete(order);
    }
    
    // Get orders by date range
    @Transactional(readOnly = true)
    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }
    
    // Get recent orders
    @Transactional(readOnly = true)
    public List<Order> getRecentOrders() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        return orderRepository.findRecentOrders(thirtyDaysAgo);
    }
    
    // Get total sales amount
    @Transactional(readOnly = true)
    public BigDecimal getTotalSalesAmount() {
        return orderRepository.getTotalSalesAmount();
    }
    
    // Get order count by status
    @Transactional(readOnly = true)
    public Long getOrderCountByStatus(OrderStatus status) {
        return orderRepository.countOrdersByStatus(status);
    }
}
