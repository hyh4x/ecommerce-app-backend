package no.group.ecommerceapp.service;

import no.group.ecommerceapp.dto.Purchase;
import no.group.ecommerceapp.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);
	
}
