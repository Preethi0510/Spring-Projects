package com.example.foodordering.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.foodordering.entity.FoodItem;
import com.example.foodordering.exception.ResourceNotFoundException;
import com.example.foodordering.repository.FoodItemRepository;
@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;
    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository=foodItemRepository;
    }
    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }
    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Food item not found with ID: "+id));
    }
    public FoodItem updateFoodItem(Long id,FoodItem foodItem) {
        FoodItem existing=getFoodItemById(id);
        existing.setName(foodItem.getName());
        existing.setCategory(foodItem.getCategory());
        existing.setPrice(foodItem.getPrice());
        existing.setAvailable(foodItem.getAvailable());
        return foodItemRepository.save(existing);
    }
    public void deleteFoodItem(Long id) {
        FoodItem foodItem=getFoodItemById(id);
        foodItemRepository.delete(foodItem);
    }
    public List<FoodItem> searchFoodByName(String name) {
        return foodItemRepository.findByNameContainingIgnoreCase(name);
    }
    public List<FoodItem> searchFoodByCategory(String category) {
        return foodItemRepository.findByCategoryIgnoreCase(category);
    }
    public List<FoodItem> getAvailableFood() {
        return foodItemRepository.findByAvailable(true);
    }
    public FoodItem updateAvailability(Long id,Boolean available) {
        FoodItem foodItem=getFoodItemById(id);
        foodItem.setAvailable(available);
        return foodItemRepository.save(foodItem);
    }
}