package com.example.foodordering.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.foodordering.entity.Customer;
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail(String email);
    boolean existsByEmail(String email);
}