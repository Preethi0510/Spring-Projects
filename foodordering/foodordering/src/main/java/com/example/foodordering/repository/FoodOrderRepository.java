package com.example.foodordering.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.foodordering.entity.FoodOrder;
public interface FoodOrderRepository extends JpaRepository<FoodOrder,Long> {
    List<FoodOrder> findByCustomerCustomerId(Long customerId);
}