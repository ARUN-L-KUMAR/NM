package com.example.ecommerce.config;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderStatus;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;
    
    @Autowired
    public DataInitializer(CustomerService customerService, 
                          ProductService productService,
                          OrderService orderService) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderService = orderService;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (customerService.getAllCustomers().isEmpty()) {
            initializeData();
        }
        System.out.println("\n==============================================");
        System.out.println("Application is ready!");
        System.out.println("API endpoints available at: http://localhost:8080");
        System.out.println("==============================================\n");
    }
    
    private void initializeData() {
        System.out.println("Initializing sample data...");
        
        // Create sample customers
        Customer customer1 = new Customer("John", "Doe", "john.doe@example.com");
        customer1.setPhone("+1234567890");
        customer1.setAddress("123 Main St, New York, NY 10001");
        customer1 = customerService.createCustomer(customer1);
        
        Customer customer2 = new Customer("Jane", "Smith", "jane.smith@example.com");
        customer2.setPhone("+1234567891");
        customer2.setAddress("456 Oak Ave, Los Angeles, CA 90001");
        customer2 = customerService.createCustomer(customer2);
        
        Customer customer3 = new Customer("Bob", "Johnson", "bob.johnson@example.com");
        customer3.setPhone("+1234567892");
        customer3.setAddress("789 Pine St, Chicago, IL 60601");
        customer3 = customerService.createCustomer(customer3);
        
        // Create sample products
        Product product1 = new Product("iPhone 15", new BigDecimal("999.99"), 50);
        product1.setDescription("Latest iPhone with advanced features");
        product1.setCategory("Electronics");
        product1.setBrand("Apple");
        product1 = productService.createProduct(product1);
        
        Product product2 = new Product("Samsung Galaxy S24", new BigDecimal("899.99"), 30);
        product2.setDescription("Flagship Android smartphone");
        product2.setCategory("Electronics");
        product2.setBrand("Samsung");
        product2 = productService.createProduct(product2);
        
        Product product3 = new Product("MacBook Pro", new BigDecimal("1999.99"), 20);
        product3.setDescription("Professional laptop for developers");
        product3.setCategory("Computers");
        product3.setBrand("Apple");
        product3 = productService.createProduct(product3);
        
        Product product4 = new Product("AirPods Pro", new BigDecimal("249.99"), 100);
        product4.setDescription("Wireless earbuds with noise cancellation");
        product4.setCategory("Audio");
        product4.setBrand("Apple");
        product4 = productService.createProduct(product4);
        
        System.out.println("Sample data initialized successfully!");
        System.out.println("- 3 Customers created");
        System.out.println("- 4 Products created");
        System.out.println("Note: Orders can be created via API endpoints");
    }
}
