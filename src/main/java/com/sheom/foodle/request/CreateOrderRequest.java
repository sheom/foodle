package com.sheom.foodle.request;

import com.sheom.foodle.model.Address;
import lombok.Data;

@Data
public class CreateOrderRequest {
 
	private Long restaurantId;
	
	private Address deliveryAddress;
	
    
}
