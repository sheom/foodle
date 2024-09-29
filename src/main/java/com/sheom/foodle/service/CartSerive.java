package com.sheom.foodle.service;

import com.sheom.foodle.Exception.CartException;
import com.sheom.foodle.Exception.CartItemException;
import com.sheom.foodle.Exception.MenuItemException;
import com.sheom.foodle.Exception.UserException;
import com.sheom.foodle.model.Cart;
import com.sheom.foodle.model.CartItem;
import com.sheom.foodle.request.AddCartItemRequest;

public interface CartSerive {

	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, MenuItemException, CartException, CartItemException;

	public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

	public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

	public Long calculateCartTotals(Cart cart) throws UserException;
	
	public Cart findCartById(Long id) throws CartException;
	
	public Cart findCartByUserId(Long userId) throws CartException, UserException;
	

	

	

}
