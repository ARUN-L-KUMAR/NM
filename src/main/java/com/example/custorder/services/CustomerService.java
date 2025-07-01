package com.example.custorder.services;

import com.example.custorder.Exception.ResourceNotFoundException;
import com.example.custorder.model.Customer;
import com.example.custorder.repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepo repo;
    public CustomerService(CustomerRepo repo) {
        this.repo = repo;
    }

    public Customer saveCustomer(Customer customer) {
        return repo.save(customer);
    }

    public List<Customer> getCustomers() {
        return repo.findAll();
    }

    public List<Customer> getAll(){
        return repo.findAll();
    }

    public Customer getById(long id) {
        return repo.findById((int) id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found with ID: " + id));
    }

//    public String deleteById(long id){
//         repo.deleteById((int) id);
//        return "Deleted";
//    }
    public String deleteById(long id) {
        Customer customer = repo.findById((int) id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found with ID: " + id));
        repo.delete(customer);
        return "Deleted Customer with ID: " + id;
    }

}
