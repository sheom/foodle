package com.sheom.foodle.controller;

import com.sheom.foodle.Exception.RestaurantException;
import com.sheom.foodle.Exception.UserException;
import com.sheom.foodle.dto.RestaurantDto;
import com.sheom.foodle.model.Restaurant;
import com.sheom.foodle.model.User;
import com.sheom.foodle.service.RestaurantService;
import com.sheom.foodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;


	@GetMapping("/restaurant/search/{name}")
	public ResponseEntity<Restaurant> findRestaurantByName(@PathVariable String name) throws RestaurantException {
		Restaurant restaurant = restaurantService.findRestaurantByName(name);

		return ResponseEntity.ok(restaurant);
	}


	@GetMapping("/restaurants")
	public ResponseEntity<List<Restaurant>> getAllRestaurants() throws RestaurantException, UserException {

		List<Restaurant> restaurants = restaurantService.getAllRestaurant();
		
		
		return ResponseEntity.ok(restaurants);
	}
	
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> findRestaurantById(@PathVariable Long id) throws RestaurantException, UserException {
		
	
			Restaurant restaurant = restaurantService.findRestaurantById(id);
			return ResponseEntity.ok(restaurant);
		
	
	}
	
	@PutMapping("/restaurant/{id}/add-favorites")
	public ResponseEntity<RestaurantDto> addToFavorite(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws RestaurantException, UserException {
		
		User user = userService.findUserProfileByJwt(jwt);
			RestaurantDto restaurant = restaurantService.addToFavorites(id, user);
			return ResponseEntity.ok(restaurant);
		
	
	}


}
