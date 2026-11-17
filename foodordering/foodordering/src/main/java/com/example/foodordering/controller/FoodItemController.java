package com.example.foodordering.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.foodordering.entity.FoodItem;
import com.example.foodordering.service.FoodItemService;
@RestController
@RequestMapping("/foods")
public class FoodItemController {
    private final FoodItemService foodItemService;
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService=foodItemService;
    }
    @PostMapping
    public ResponseEntity<FoodItem> addFoodItem(@Valid @RequestBody FoodItem foodItem) {
        return new ResponseEntity<>(foodItemService.addFoodItem(foodItem),HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable Long id) {
        return ResponseEntity.ok(foodItemService.getFoodItemById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> updateFoodItem(@PathVariable Long id,@Valid @RequestBody FoodItem foodItem) {
        return ResponseEntity.ok(foodItemService.updateFoodItem(id,foodItem));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.ok("Food item deleted successfully");
    }
    @GetMapping("/search")
    public ResponseEntity<List<FoodItem>> searchFoodByName(@RequestParam String name) {
        return ResponseEntity.ok(foodItemService.searchFoodByName(name));
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<FoodItem>> searchFoodByCategory(@PathVariable String category) {
        return ResponseEntity.ok(foodItemService.searchFoodByCategory(category));
    }
    @GetMapping("/available")
    public ResponseEntity<List<FoodItem>> getAvailableFood() {
        return ResponseEntity.ok(foodItemService.getAvailableFood());
    }
    @PutMapping("/{id}/availability")
    public ResponseEntity<FoodItem> updateAvailability(@PathVariable Long id,@RequestParam Boolean available) {
        return ResponseEntity.ok(foodItemService.updateAvailability(id,available));
    }
}