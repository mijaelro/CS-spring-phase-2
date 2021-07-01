package com.mijael.CSSpring.services;

import com.mijael.CSSpring.exceptions.LoggInException;

public interface ClientService {

	public boolean logIn(String email, String password) throws LoggInException;

}
