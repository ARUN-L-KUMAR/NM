package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // Find orders by customer ID
    List<Order> findByCustomerId(Long customerId);
    
    // Find orders by customer ID ordered by order date descending
    List<Order> findByCustomerIdOrderByOrderDateDesc(Long customerId);
    
    // Find orders by status
    List<Order> findByStatus(OrderStatus status);
    
    // Find orders by status ordered by order date
    List<Order> findByStatusOrderByOrderDateDesc(OrderStatus status);
    
    // Find orders by date range
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find orders by total amount range
    List<Order> findByTotalAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);
    
    // Find orders by total amount greater than or equal to
    List<Order> findByTotalAmountGreaterThanEqual(BigDecimal minAmount);
    
    // Find orders by customer and status
    List<Order> findByCustomerIdAndStatus(Long customerId, OrderStatus status);
    
    // Find orders by customer and date range
    List<Order> findByCustomerIdAndOrderDateBetween(Long customerId, LocalDateTime startDate, LocalDateTime endDate);
    
    // Find recent orders (last 30 days)
    @Query("SELECT o FROM Order o WHERE o.orderDate >= :thirtyDaysAgo ORDER BY o.orderDate DESC")
    List<Order> findRecentOrders(@Param("thirtyDaysAgo") LocalDateTime thirtyDaysAgo);
    
    // Find orders by shipping address containing keyword
    List<Order> findByShippingAddressContainingIgnoreCase(String address);
    
    // Get total sales amount
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.status != 'CANCELLED'")
    BigDecimal getTotalSalesAmount();
    
    // Get total sales amount by date range
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate AND o.status != 'CANCELLED'")
    BigDecimal getTotalSalesAmountByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    // Get order count by status
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countOrdersByStatus(@Param("status") OrderStatus status);
    
    // Find top customers by order count
    @Query("SELECT o.customer.id, COUNT(o) as orderCount FROM Order o " +
           "GROUP BY o.customer.id ORDER BY orderCount DESC")
    List<Object[]> findTopCustomersByOrderCount();
    
    // Find top customers by total amount spent
    @Query("SELECT o.customer.id, SUM(o.totalAmount) as totalSpent FROM Order o " +
           "WHERE o.status != 'CANCELLED' " +
           "GROUP BY o.customer.id ORDER BY totalSpent DESC")
    List<Object[]> findTopCustomersByTotalSpent();
    
    // Get monthly sales report
    @Query("SELECT YEAR(o.orderDate), MONTH(o.orderDate), SUM(o.totalAmount), COUNT(o) " +
           "FROM Order o WHERE o.status != 'CANCELLED' " +
           "GROUP BY YEAR(o.orderDate), MONTH(o.orderDate) " +
           "ORDER BY YEAR(o.orderDate) DESC, MONTH(o.orderDate) DESC")
    List<Object[]> getMonthlySalesReport();
}
