package com.sheom.foodle.service;

import com.sheom.foodle.model.Order;
import com.sheom.foodle.model.Restaurant;
import com.sheom.foodle.model.User;

public interface NotificationService {
	
	public void sendOrderStatusNotification(User user, Order order);
	public void sendRestaurantNotification(Restaurant restaurant, String message);
	public void sendPromotionalNotification(User user, String message);

}
