package com.mijael.CSSpring.impl;

import org.springframework.stereotype.Service;

import com.mijael.CSSpring.exceptions.LoggInException;
import com.mijael.CSSpring.repos.CompanyRepository;
import com.mijael.CSSpring.repos.CouponRepository;
import com.mijael.CSSpring.repos.CustomerRepository;
import com.mijael.CSSpring.services.ClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class ClientServiceImpl implements ClientService {

	final CompanyRepository companyRepository;
	final CustomerRepository customerRepository;
	final CouponRepository couponRepository;

	@Override
	public abstract boolean logIn(String email, String password) throws LoggInException;
}
