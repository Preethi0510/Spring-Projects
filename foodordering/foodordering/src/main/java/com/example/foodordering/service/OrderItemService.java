package com.example.foodordering.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.foodordering.entity.OrderItem;
import com.example.foodordering.exception.ResourceNotFoundException;
import com.example.foodordering.repository.FoodOrderRepository;
import com.example.foodordering.repository.OrderItemRepository;
@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final FoodOrderRepository foodOrderRepository;
    public OrderItemService(OrderItemRepository orderItemRepository,FoodOrderRepository foodOrderRepository) {
        this.orderItemRepository=orderItemRepository;
        this.foodOrderRepository=foodOrderRepository;
    }
    public List<OrderItem> getItemsByOrder(Long orderId) {
        if(!foodOrderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException("Order not found with ID: "+orderId);
        }
        return orderItemRepository.findByFoodOrderOrderId(orderId);
    }
}