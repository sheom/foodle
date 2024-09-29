package com.sheom.foodle.controller;

import com.zosh.Exception.MenuItemException;
import com.zosh.model.MenuItem;
import com.zosh.service.MenuItemService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuItemController {
	@Autowired
	private MenuItemService menuItemService;
	@Autowired
	private UserService userService;

	@GetMapping("/menu/filter")
	public ResponseEntity<List<MenuItem>> getItemByFilter(@RequestParam boolean isVegetarian,
			@RequestParam boolean isVegan, @RequestParam boolean isGlutenFree) throws MenuItemException {
		List<MenuItem> itemsByCriteria = menuItemService.getMenuItemsByCriteria(isVegetarian, isVegan, isGlutenFree);
		return ResponseEntity.ok(itemsByCriteria);
	}

	@GetMapping("/menu/search")
	public ResponseEntity<List<MenuItem>> getMenuItemByName(@RequestParam String name)  {
		List<MenuItem> menuItem = menuItemService.searchMenuItem(name);
		return ResponseEntity.ok(menuItem);
	}
	@GetMapping("/menu/restaurant/{restaurantId}")
	public ResponseEntity<List<MenuItem>> getMenuItemByRestaurantId(@PathVariable Long restaurantId) throws MenuItemException {
		List<MenuItem> menuItems= menuItemService.getRestaurantMenuItems(restaurantId);
		return ResponseEntity.ok(menuItems);
	}

}
