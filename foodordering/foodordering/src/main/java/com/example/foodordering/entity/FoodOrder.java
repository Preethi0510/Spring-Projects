package com.example.foodordering.entity;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="orders")
public class FoodOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name="customer_id",nullable=false)
    private Customer customer;
    private LocalDateTime orderDate;
    @NotNull(message="Total amount is required")
    @DecimalMin(value="0.0",message="Total amount cannot be negative")
    private Double totalAmount;
    @NotBlank(message="Order status is required")
    private String status;
    @OneToMany(mappedBy="foodOrder",cascade=CascadeType.ALL)
    private List<OrderItem> orderItems;
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId=orderId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer=customer;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate=orderDate;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount=totalAmount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status=status;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems=orderItems;
    }
}