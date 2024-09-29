package com.sheom.foodle.service;

import com.stripe.exception.StripeException;
import com.sheom.foodle.model.Order;
import com.sheom.foodle.model.PaymentResponse;

public interface PaymentService {
	
	public PaymentResponse generatePaymentLink(Order order) throws StripeException;

}
