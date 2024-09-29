package com.sheom.foodle.service;

import com.stripe.exception.StripeException;
import com.sheom.foodle.Exception.CartException;
import com.sheom.foodle.Exception.OrderException;
import com.sheom.foodle.Exception.RestaurantException;
import com.sheom.foodle.Exception.UserException;
import com.sheom.foodle.model.Order;
import com.sheom.foodle.model.PaymentResponse;
import com.sheom.foodle.model.User;
import com.sheom.foodle.request.CreateOrderRequest;

import java.util.List;

public interface OrderService {
	
	 public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, RestaurantException, CartException, StripeException;
	 
	 public Order updateOrder(Long orderId, String orderStatus) throws OrderException;
	 
	 public void cancelOrder(Long orderId) throws OrderException;
	 
	 public List<Order> getUserOrders(Long userId) throws OrderException;
	 
	 public List<Order> getOrdersOfRestaurant(Long restaurantId) throws OrderException, RestaurantException;
	 

}
