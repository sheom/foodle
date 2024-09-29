package com.sheom.foodle.service;

import com.sheom.foodle.Exception.RestaurantException;
import com.sheom.foodle.dto.RestaurantDto;
import com.sheom.foodle.model.Restaurant;
import com.sheom.foodle.model.User;
import com.sheom.foodle.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

	public Restaurant createRestaurant(CreateRestaurantRequest req,User user);

	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
			throws RestaurantException;

	public void deleteRestaurant(Long restaurantId) throws RestaurantException;

	public Restaurant findRestaurantByName(String Name) throws RestaurantException;

	public List<Restaurant> getRestaurantsByName(String name);
	
	public List<Restaurant>getAllRestaurant();
	
	public Restaurant findRestaurantById(Long id) throws RestaurantException;
//	 public  List<Restaurant> getRestaurantsByLocation(String location);
	public List<Restaurant> getRestaurantsByUserId(Long userId) throws RestaurantException;
	
	public RestaurantDto addToFavorites(Long restaurantId,User user) throws RestaurantException;

}
