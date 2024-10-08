package com.sheom.foodle.controller;

import com.sheom.foodle.Exception.RestaurantException;
import com.sheom.foodle.Exception.UserException;
import com.sheom.foodle.model.Restaurant;
import com.sheom.foodle.model.User;
import com.sheom.foodle.request.CreateRestaurantRequest;
import com.sheom.foodle.response.ApiResponse;
import com.sheom.foodle.service.RestaurantService;
import com.sheom.foodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/restaurant")
	public ResponseEntity<Restaurant> createRestaurant(
			@RequestBody CreateRestaurantRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException {
		User user = userService.findUserProfileByJwt(jwt);
		
//			System.out.println("----TRUE___-----");
			Restaurant restaurant = restaurantService.createRestaurant(req,user);
			return ResponseEntity.ok(restaurant);
		
	}


	@PutMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody CreateRestaurantRequest req,
			@RequestHeader("Authorization") String jwt) throws RestaurantException, UserException {
		User user = userService.findUserProfileByJwt(jwt);
		
			Restaurant restaurant = restaurantService.updateRestaurant(id, req);
			return ResponseEntity.ok(restaurant);
		
	}

	@DeleteMapping("/restaurant/{id}")
	public ResponseEntity<ApiResponse> deleteRestaurantById(@PathVariable("id") Long restaurantId,
			@RequestHeader("Authorization") String jwt) throws RestaurantException, UserException {
		User user = userService.findUserProfileByJwt(jwt);
		
			restaurantService.deleteRestaurant(restaurantId);
			
			ApiResponse res=new ApiResponse("Restaurant Deleted with id Successfully",true);
			return ResponseEntity.ok(res);
	
		
	}

	
	@GetMapping("/restaurants/user")
	public ResponseEntity<List<Restaurant>> getUsersRestaurant(@RequestHeader("Authorization") String jwt) throws UserException, RestaurantException {
		User user = userService.findUserProfileByJwt(jwt);
		List<Restaurant> restaurants = restaurantService.getRestaurantsByUserId(user.getId());
		return ResponseEntity.ok(restaurants);
	}
	
	
	

}
