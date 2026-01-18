package com.example.ecommerce.service;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    // Create a new customer
    public Customer createCustomer(Customer customer) {
        // Check if email already exists
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Customer with email " + customer.getEmail() + " already exists");
        }
        return customerRepository.save(customer);
    }
    
    // Get all customers
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    // Get customer by ID
    @Transactional(readOnly = true)
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    
    // Get customer by email
    @Transactional(readOnly = true)
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    
    // Update customer
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        
        // Check if email is being changed and if new email already exists
        if (!customer.getEmail().equals(customerDetails.getEmail()) && 
            customerRepository.existsByEmail(customerDetails.getEmail())) {
            throw new IllegalArgumentException("Customer with email " + customerDetails.getEmail() + " already exists");
        }
        
        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());
        customer.setAddress(customerDetails.getAddress());
        
        return customerRepository.save(customer);
    }
    
    // Delete customer
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }
    
    // Search customers by name
    @Transactional(readOnly = true)
    public List<Customer> searchCustomersByName(String name) {
        return customerRepository.findByFullNameContainingIgnoreCase(name);
    }
    
    // Search customers by first name
    @Transactional(readOnly = true)
    public List<Customer> searchCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstNameContainingIgnoreCase(firstName);
    }
    
    // Search customers by last name
    @Transactional(readOnly = true)
    public List<Customer> searchCustomersByLastName(String lastName) {
        return customerRepository.findByLastNameContainingIgnoreCase(lastName);
    }
    
    // Get customers with orders
    @Transactional(readOnly = true)
    public List<Customer> getCustomersWithOrders() {
        return customerRepository.findCustomersWithOrders();
    }
    
    // Get customers without orders
    @Transactional(readOnly = true)
    public List<Customer> getCustomersWithoutOrders() {
        return customerRepository.findCustomersWithoutOrders();
    }
    
    // Check if customer exists by email
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
    
    // Get customer by phone
    @Transactional(readOnly = true)
    public Optional<Customer> getCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }
    
    // Search customers by address
    @Transactional(readOnly = true)
    public List<Customer> searchCustomersByAddress(String address) {
        return customerRepository.findByAddressContainingIgnoreCase(address);
    }
}
