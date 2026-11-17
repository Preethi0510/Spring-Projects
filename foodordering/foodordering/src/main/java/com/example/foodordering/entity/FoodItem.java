package com.example.foodordering.entity;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="food_items")
public class FoodItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long foodId;
    @NotBlank(message="Food name is required")
    private String name;
    @NotBlank(message="Category is required")
    private String category;
    @NotNull(message="Price is required")
    @DecimalMin(value="0.01",message="Price must be greater than zero")
    private Double price;
    @NotNull(message="Availability is required")
    private Boolean available=true;
    @JsonIgnore
    @OneToMany(mappedBy="foodItem")
    private List<OrderItem> orderItems;
    public Long getFoodId() {
        return foodId;
    }
    public void setFoodId(Long foodId) {
        this.foodId=foodId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category=category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price=price;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available=available;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems=orderItems;
    }
}