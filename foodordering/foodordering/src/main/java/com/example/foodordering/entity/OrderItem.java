package com.example.foodordering.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long itemId;
    @ManyToOne
    @JoinColumn(name="order_id",nullable=false)
    @JsonIgnore
    private FoodOrder foodOrder;
    @ManyToOne
    @JoinColumn(name="food_id",nullable=false)
    private FoodItem foodItem;
    @NotNull(message="Quantity is required")
    @Min(value=1,message="Quantity must be at least 1")
    private Integer quantity;
    @NotNull(message="Subtotal is required")
    @DecimalMin(value="0.01",message="Subtotal must be greater than zero")
    private Double subtotal;
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId=itemId;
    }
    public FoodOrder getFoodOrder() {
        return foodOrder;
    }
    public void setFoodOrder(FoodOrder foodOrder) {
        this.foodOrder=foodOrder;
    }
    public FoodItem getFoodItem() {
        return foodItem;
    }
    public void setFoodItem(FoodItem foodItem) {
        this.foodItem=foodItem;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity=quantity;
    }
    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal=subtotal;
    }
}