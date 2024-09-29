package com.sheom.foodle.controller;

import com.zosh.Exception.MenuItemException;
import com.zosh.Exception.RestaurantException;
import com.zosh.Exception.UserException;
import com.zosh.model.MenuItem;
import com.zosh.model.User;
import com.zosh.request.CreateMenuItemRequest;
import com.zosh.service.MenuItemService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminMenuItemController {
	
	@Autowired
	private MenuItemService menuItemService;
	@Autowired
	private UserService userService;

	@PostMapping("/menu")
	public ResponseEntity<MenuItem> createItem(
			@RequestBody CreateMenuItemRequest item, 
			@RequestHeader("Authorization") String jwt)
			throws MenuItemException, UserException, RestaurantException {
		System.out.println("req-controller ----"+item);
		User user = userService.findUserProfileByJwt(jwt);
		

			MenuItem menuItem = menuItemService.createMenuItem(item);


			return ResponseEntity.ok(menuItem);

	}

	@PutMapping("/menu/{id}")
	public ResponseEntity<MenuItem> updateItem(@PathVariable Long id, @RequestBody MenuItem upddatedMenuItem,
			@RequestHeader("Authorization") String jwt) throws MenuItemException, UserException {
		User user = userService.findUserProfileByJwt(jwt);
		if (user.getRole().equals("ROLE_RESTAURANT_OWNER")) {
			MenuItem updateMenuItem = menuItemService.updateMenuItem(id, upddatedMenuItem);
			return ResponseEntity.ok(updateMenuItem);
		}
		throw new UserException("User not authorize to update menu");
	}

	@DeleteMapping("/menu/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
			throws UserException {
		User user = userService.findUserProfileByJwt(jwt);
		if (user.getRole().equals("ROLE_RESTAURANT_OWNER")) {
			menuItemService.deleteMenuItem(id);
			return ResponseEntity.ok("Menu item deleted");
		}
		throw new UserException("User not authorize to delete menu");
	}

	

	@GetMapping("/menu/search")
	public ResponseEntity<List<MenuItem>> getMenuItemByName(@RequestParam String name)  {
		List<MenuItem> menuItem = menuItemService.searchMenuItem(name);
		return ResponseEntity.ok(menuItem);
	}
	
	
	
	
	

}
