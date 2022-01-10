package com.customer.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.application.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
