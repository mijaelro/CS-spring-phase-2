package com.mijael.CSSpring.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mijael.CSSpring.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean existsByName(String name);

	Company findByEmailAndPassword(String email, String password);

	Company findByName(String name);

	Company findByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

	boolean existsByEmail(String email);

	Company findByNameOrEmail(String name, String email);

	
}
