package no.group.ecommerceapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import no.group.ecommerceapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByEmail(String email);
	
}
