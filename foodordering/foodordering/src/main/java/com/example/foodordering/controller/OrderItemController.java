package com.example.foodordering.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.foodordering.entity.OrderItem;
import com.example.foodordering.service.OrderItemService;
@RestController
@RequestMapping("/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService=orderItemService;
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getItemsByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderItemService.getItemsByOrder(orderId));
    }
}