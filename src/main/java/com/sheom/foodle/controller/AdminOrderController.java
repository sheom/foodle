package com.sheom.foodle.controller;

import com.sheom.foodle.Exception.OrderException;
import com.sheom.foodle.Exception.RestaurantException;
import com.sheom.foodle.model.Order;
import com.sheom.foodle.service.OrderService;
import com.sheom.foodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) throws OrderException{
    	if(orderId!=null) {
    		orderService.cancelOrder(orderId);
    	return ResponseEntity.ok("Order deleted with id)"+orderId);
    }else return new ResponseEntity<String>(HttpStatus.BAD_REQUEST) ;
    }
    
    
    @GetMapping("/order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getAllRestaurantOrders(@PathVariable Long restaurantId) throws OrderException, RestaurantException{
    	if(restaurantId!=null) {
    		List<Order> orders = orderService.getOrdersOfRestaurant(restaurantId);
    		return ResponseEntity.ok(orders);
    		
    	}
    	else {
    		return new ResponseEntity<List<Order>>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PutMapping("/orders/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrders(@PathVariable Long orderId,@PathVariable String orderStatus) throws OrderException, RestaurantException{
    	
    		Order orders = orderService.updateOrder(orderId, orderStatus);
    		return ResponseEntity.ok(orders);
    		
    }

}
