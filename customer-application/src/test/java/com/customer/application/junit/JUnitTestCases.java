package com.customer.application.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.customer.application.entity.Customer;
import com.customer.application.repository.CustomerRepository;


class JUnitTestCases {
	
	private org.slf4j.Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EntityManager em;

	@Test
	public void findById_CustomerPresent() {
		Optional<Customer> customerOptional=customerRepository.findById(1L);
		assertTrue(customerOptional.isPresent());
	}
	
	@Test
	public void findUsingName() {
		logger.info("FindByName -> {}",customerRepository.findById(2L));
	}
	
	@Test
	@Transactional
	public void retrieveCustomerAndPreferredFoodDetails() {
		Customer customer = em.find(Customer.class, 20001L);
		logger.info("Customer -> {}",customer);
		
		logger.info("PreferredFood -> {}",customer.getPreferredFood());
		
	}
	
	@Test
	@Transactional
	public void retrieveCustomerAndageDetails() {
		Customer customer = em.find(Customer.class, 20001L);
		logger.info("Customer -> {}",customer);
		
		logger.info("Age -> {}",customer.getAge());
		
	}
	
}
