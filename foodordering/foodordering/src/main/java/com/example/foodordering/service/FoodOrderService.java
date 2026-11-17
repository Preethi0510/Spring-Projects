package com.example.foodordering.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.foodordering.entity.Customer;
import com.example.foodordering.entity.FoodItem;
import com.example.foodordering.entity.FoodOrder;
import com.example.foodordering.entity.OrderItem;
import com.example.foodordering.exception.BadRequestException;
import com.example.foodordering.exception.ResourceNotFoundException;
import com.example.foodordering.repository.CustomerRepository;
import com.example.foodordering.repository.FoodItemRepository;
import com.example.foodordering.repository.FoodOrderRepository;
@Service
public class FoodOrderService {
    private final FoodOrderRepository foodOrderRepository;
    private final CustomerRepository customerRepository;
    private final FoodItemRepository foodItemRepository;
    public FoodOrderService(FoodOrderRepository foodOrderRepository,CustomerRepository customerRepository,FoodItemRepository foodItemRepository) {
        this.foodOrderRepository=foodOrderRepository;
        this.customerRepository=customerRepository;
        this.foodItemRepository=foodItemRepository;
    }
    @Transactional
    public FoodOrder placeOrder(FoodOrder order) {
        if(order.getCustomer()==null || order.getCustomer().getCustomerId()==null) {
            throw new BadRequestException("Customer ID is required");
        }
        Customer customer=customerRepository.findById(order.getCustomer().getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        if(order.getOrderItems()==null || order.getOrderItems().isEmpty()) {
            throw new BadRequestException("Order must contain at least one food item");
        }
        double total=0;
        List<OrderItem> items=new ArrayList<>();
        for(OrderItem item:order.getOrderItems()) {
            if(item.getFoodItem()==null || item.getFoodItem().getFoodId()==null) {
                throw new BadRequestException("Food ID is required");
            }
            if(item.getQuantity()==null || item.getQuantity()<1) {
                throw new BadRequestException("Quantity must be at least 1");
            }
            FoodItem food=foodItemRepository.findById(item.getFoodItem().getFoodId()).orElseThrow(() -> new ResourceNotFoundException("Food item not found"));
            if(!Boolean.TRUE.equals(food.getAvailable())) {
                throw new BadRequestException(food.getName()+" is currently unavailable");
            }
            double subtotal=food.getPrice()*item.getQuantity();
            item.setFoodItem(food);
            item.setSubtotal(subtotal);
            item.setFoodOrder(order);
            total+=subtotal;
            items.add(item);
        }
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(total);
        order.setStatus("PENDING");
        order.setOrderItems(items);
        return foodOrderRepository.save(order);
    }
    public List<FoodOrder> getAllOrders() {
        return foodOrderRepository.findAll();
    }
    public FoodOrder getOrderById(Long id) {
        return foodOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: "+id));
    }
    public List<FoodOrder> getOrdersByCustomer(Long customerId) {
        if(!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found with ID: "+customerId);
        }
        return foodOrderRepository.findByCustomerCustomerId(customerId);
    }
    public FoodOrder cancelOrder(Long id) {
        FoodOrder order=getOrderById(id);
        if(!"PENDING".equals(order.getStatus())) {
            throw new BadRequestException("Only pending orders can be cancelled");
        }
        order.setStatus("CANCELLED");
        return foodOrderRepository.save(order);
    }
    public FoodOrder updateOrderStatus(Long id,String status) {
        FoodOrder order=getOrderById(id);
        if(status==null || !List.of("PENDING","CONFIRMED","PREPARING","OUT_FOR_DELIVERY","DELIVERED","CANCELLED").contains(status.toUpperCase())) {
            throw new BadRequestException("Invalid order status");
        }
        if("CANCELLED".equals(order.getStatus()) || "DELIVERED".equals(order.getStatus())) {
            throw new BadRequestException("This order can no longer be updated");
        }
        String newStatus=status.toUpperCase();
        if("CANCELLED".equals(newStatus) && !"PENDING".equals(order.getStatus())) {
            throw new BadRequestException("Only pending orders can be cancelled");
        }
        order.setStatus(newStatus);
        return foodOrderRepository.save(order);
    }
}