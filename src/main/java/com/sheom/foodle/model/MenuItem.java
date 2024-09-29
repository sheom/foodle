package com.sheom.foodle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private Long price;
    
    @ManyToOne
    private Category category;
    private String imageUrl;
    private boolean availabilityStatus;

    
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    
    private boolean isVegetarian;
    private boolean isGlutenFree;
    private boolean isVegan;
    
    private Long discountedPrice;
    
    private Integer discount;
    

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    }

