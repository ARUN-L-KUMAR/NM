package com.example.custorder.controller;

import com.example.custorder.model.Customer;
import com.example.custorder.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public Customer save(@RequestBody Customer customer) {

        return customerService.saveCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAll(){
        return customerService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable(name = "id") long id) {
        Customer customer = customerService.getById(id);
        return ResponseEntity.ok(customer);
    }

//    @DeleteMapping("{id}")
//    public String deleteById(@PathVariable(name = "id") long id){
//        return customerService.deleteById(id);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") long id) {
        String response = customerService.deleteById(id);
        return ResponseEntity.ok(response);
    }
}

