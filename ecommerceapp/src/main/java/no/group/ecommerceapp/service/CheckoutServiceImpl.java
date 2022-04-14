package no.group.ecommerceapp.service;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import no.group.ecommerceapp.dao.CustomerRepository;
import no.group.ecommerceapp.dto.Purchase;
import no.group.ecommerceapp.dto.PurchaseResponse;
import no.group.ecommerceapp.entity.Customer;
import no.group.ecommerceapp.entity.Order;
import no.group.ecommerceapp.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private CustomerRepository customerRepository;
	
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {

		Order order = purchase.getOrder();
		
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		Set<OrderItem> items = purchase.getOrderItems();
		items.forEach(item -> order.add(item));
		
		order.setShippingAddress(purchase.getShippingAddress());
		order.setBillingAddress(purchase.getBillingAddress());
		
		Customer customer = purchase.getCustomer();
		
		//check if customer already exists
		String customerEmail = customer.getEmail();
		Customer existingCustomer = customerRepository.findByEmail(customerEmail);
		if(existingCustomer != null) {
			customer = existingCustomer;
		}
		
		
		customer.add(order);
		
		customerRepository.save(customer);
				
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		
		//random UUID
		
		return UUID.randomUUID().toString();
	}

}
