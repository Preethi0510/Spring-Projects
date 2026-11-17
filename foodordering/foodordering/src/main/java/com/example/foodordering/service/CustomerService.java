package com.example.foodordering.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.foodordering.entity.Customer;
import com.example.foodordering.exception.DuplicateResourceException;
import com.example.foodordering.exception.ResourceNotFoundException;
import com.example.foodordering.repository.CustomerRepository;
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository=customerRepository;
    }
    public Customer addCustomer(Customer customer) {
        if(customerRepository.existsByEmail(customer.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: "+id));
    }
    public Customer updateCustomer(Long id,Customer customer) {
        Customer existing=getCustomerById(id);
        customerRepository.findByEmail(customer.getEmail()).ifPresent(found -> {
            if(!found.getCustomerId().equals(id)) {
                throw new DuplicateResourceException("Email already exists");
            }
        });
        existing.setName(customer.getName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        existing.setAddress(customer.getAddress());
        return customerRepository.save(existing);
    }
    public void deleteCustomer(Long id) {
        Customer customer=getCustomerById(id);
        customerRepository.delete(customer);
    }
}