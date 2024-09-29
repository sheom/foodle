package com.sheom.foodle.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuItemRequest {
	

    
    private String name;
    private String description;
    private Long price;
    
  
    private String category;
    private String imageUrl;
    private boolean availabilityStatus;

   
    private Long restaurantId;
    
    private boolean isVegetarian;
    private boolean isGlutenFree;
    private boolean isVegan;
	

}
