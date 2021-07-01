package com.mijael.CSSpring.services;

import org.springframework.stereotype.Service;

import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.exceptions.LoggInException;
import com.mijael.CSSpring.impl.ClientServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginManagerService {

	private final AdminService adminService;
	private final CompanyService companyService;
	private final CustomerService customerService;

	public ClientServiceImpl logIn(String email, String password, ClientType clientType) throws LoggInException {
		ClientServiceImpl clientService;
		switch (clientType) {
		case ADMINISTRATOR:
			clientService = (ClientServiceImpl) adminService;
			if (clientService.logIn(email, password)) {
				return clientService;
			}

		case COMPANY:
			clientService = (ClientServiceImpl) companyService;
			if (clientService.logIn(email, password)) {
				return clientService;
			}
		case CUSTOMER:
			clientService = (ClientServiceImpl) customerService;
			if (clientService.logIn(email, password)) {
				return clientService;
			}
		}
		throw new LoggInException("Error,Incorrect client type");
	}

}
