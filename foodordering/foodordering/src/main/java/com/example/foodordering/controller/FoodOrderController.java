package com.example.foodordering.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.foodordering.entity.FoodOrder;
import com.example.foodordering.service.FoodOrderService;
@RestController
@RequestMapping("/orders")
public class FoodOrderController {
    private final FoodOrderService foodOrderService;
    public FoodOrderController(FoodOrderService foodOrderService) {
        this.foodOrderService=foodOrderService;
    }
    @PostMapping
    public ResponseEntity<FoodOrder> placeOrder(@RequestBody FoodOrder order) {
        return new ResponseEntity<>(foodOrderService.placeOrder(order),HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<FoodOrder>> getAllOrders() {
        return ResponseEntity.ok(foodOrderService.getAllOrders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(foodOrderService.getOrderById(id));
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<FoodOrder>> getOrdersByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(foodOrderService.getOrdersByCustomer(customerId));
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<FoodOrder> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.ok(foodOrderService.cancelOrder(id));
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<FoodOrder> updateOrderStatus(@PathVariable Long id,@RequestParam String status) {
        return ResponseEntity.ok(foodOrderService.updateOrderStatus(id,status));
    }
}