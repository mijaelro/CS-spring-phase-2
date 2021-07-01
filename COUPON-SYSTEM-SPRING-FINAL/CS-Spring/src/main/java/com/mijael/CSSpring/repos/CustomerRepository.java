package com.mijael.CSSpring.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mijael.CSSpring.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByEmailAndPassword(String email, String password);

	Customer findByEmail(String email);

	Customer findByFirstName(String name);

	boolean existsByEmailAndPassword(String email, String password) ;

	boolean existsByEmail(String email);

}
