package com.customer.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.application.entity.Customer;
import com.customer.application.exception.ResourceNotFoundException;
import com.customer.application.repository.CustomerRepository;



@RestController
@RequestMapping("/api/e1/")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers")
	public List<Customer> getAllEmployees(){
		return customerRepository.findAll();
	}
	
	@PostMapping("/customers")
	public Customer createEmployee(@RequestBody Customer employee) {
		return customerRepository.save(employee);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getEmployeeById(@PathVariable Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));
		return ResponseEntity.ok(customer);
	}
	
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateEmployee(@PathVariable Long id, @RequestBody Customer customerDetails){
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));
		
		customer.setFirstName(customerDetails.getFirstName());
		customer.setLastName(customerDetails.getLastName());
		customer.setGender(customerDetails.getGender());
		customer.setAge(customerDetails.getAge());
		customer.setPreferredFood(customerDetails.getPreferredFood());	
		Customer updatedEmployee = customerRepository.save(customer);
		return ResponseEntity.ok(updatedEmployee);
	}
	

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));
		
		customerRepository.delete(customer);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
