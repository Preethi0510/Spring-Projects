package com.example.foodordering.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.foodordering.entity.FoodItem;
public interface FoodItemRepository extends JpaRepository<FoodItem,Long> {
    List<FoodItem> findByNameContainingIgnoreCase(String name);
    List<FoodItem> findByCategoryIgnoreCase(String category);
    List<FoodItem> findByAvailable(Boolean available);
}