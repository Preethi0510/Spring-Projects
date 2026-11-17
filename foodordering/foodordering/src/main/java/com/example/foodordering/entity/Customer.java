package com.example.foodordering.entity;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long customerId;
    @NotBlank(message="Customer name is required")
    @Size(min=3,max=50,message="Name must contain 3 to 50 characters")
    private String name;
    @NotBlank(message="Email is required")
    @Email(message="Enter a valid email")
    @Column(unique=true,nullable=false)
    private String email;
    @NotBlank(message="Phone number is required")
    @Pattern(regexp="^[0-9]{10}$",message="Phone number must contain exactly 10 digits")
    private String phone;
    @NotBlank(message="Address is required")
    private String address;
    @JsonIgnore
    @OneToMany(mappedBy="customer")
    private List<FoodOrder> orders;
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId=customerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email=email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone=phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address=address;
    }
    public List<FoodOrder> getOrders() {
        return orders;
    }
    public void setOrders(List<FoodOrder> orders) {
        this.orders=orders;
    }
}