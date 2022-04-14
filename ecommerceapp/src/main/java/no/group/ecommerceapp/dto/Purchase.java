package no.group.ecommerceapp.dto;

import java.util.Set;

import lombok.Data;
import no.group.ecommerceapp.entity.Address;
import no.group.ecommerceapp.entity.Customer;
import no.group.ecommerceapp.entity.Order;
import no.group.ecommerceapp.entity.OrderItem;

@Data
public class Purchase {
	
	private Customer customer;
	
	private Address shippingAddress;
	
	private Address billingAddress;
	
	private Order order;
	
	private Set<OrderItem> orderItems;
}
