package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Find customer by email
    Optional<Customer> findByEmail(String email);
    
    // Check if customer exists by email
    boolean existsByEmail(String email);
    
    // Find customers by first name (case insensitive)
    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
    
    // Find customers by last name (case insensitive)
    List<Customer> findByLastNameContainingIgnoreCase(String lastName);
    
    // Find customers by full name (case insensitive)
    @Query("SELECT c FROM Customer c WHERE " +
           "LOWER(CONCAT(c.firstName, ' ', c.lastName)) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    List<Customer> findByFullNameContainingIgnoreCase(@Param("fullName") String fullName);
    
    // Find customers by phone number
    Optional<Customer> findByPhone(String phone);
    
    // Find customers by address containing keyword
    List<Customer> findByAddressContainingIgnoreCase(String address);
    
    // Find customers with orders
    @Query("SELECT DISTINCT c FROM Customer c JOIN c.orders o")
    List<Customer> findCustomersWithOrders();
    
    // Find customers without orders
    @Query("SELECT c FROM Customer c WHERE c.orders IS EMPTY")
    List<Customer> findCustomersWithoutOrders();
}
