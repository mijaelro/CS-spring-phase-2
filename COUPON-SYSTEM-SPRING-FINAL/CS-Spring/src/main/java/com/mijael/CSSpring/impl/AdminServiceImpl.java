package com.mijael.CSSpring.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.LoggInException;
import com.mijael.CSSpring.exceptions.SaveException;
import com.mijael.CSSpring.repos.CompanyRepository;
import com.mijael.CSSpring.repos.CouponRepository;
import com.mijael.CSSpring.repos.CustomerRepository;
import com.mijael.CSSpring.services.AdminService;

@Service
public class AdminServiceImpl extends ClientServiceImpl implements AdminService {

	public AdminServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
	}

	@Override
	public boolean logIn(String email, String password) throws LoggInException {
		boolean isLoggedIn = (email.equals("admin@admin.com") && password.equals("admin"));
		if (isLoggedIn == false) {
			throw new LoggInException("Error , unable to logg in .. try again");
		}
		return isLoggedIn;
	}

	@Override
	public void addCompany(Company company) throws IllegalActionException, SaveException {
		if (companyRepository.existsByName(company.getName())) {
			throw new IllegalActionException("The name for the company: " + company.getId()
					+ " already exists... try with different attributes");
		}
		if (companyRepository.existsByEmail(company.getEmail())) {
			throw new IllegalActionException("The email for the company: " + company.getId()
					+ " already exists... try with different attributes ");
		}
		try {
			companyRepository.saveAndFlush(company);
		} catch (Exception e) {
			throw new SaveException(e.getMessage());
		}
	}

	@Override
	public Company getOneCompany(int id) throws IllegalActionException {
		return companyRepository.findById(id)
				.orElseThrow(() -> new IllegalActionException("there is no company with the id: " + id));
	}

	@Override
	public void updateCompany(Company company) throws IllegalActionException, SaveException {
		Company toUpdate = companyRepository.findById(company.getId())
				.orElseThrow(() -> new IllegalActionException("there is no company by the id: " + company.getId()));
		try {
			companyRepository.saveAndFlush(company);
		} catch (Exception e) {
			throw new SaveException("name or email (which are unique) already exist,try with different attributes");
		}
	}

	@Override
	public void DeleteCompany(int id) throws IllegalActionException {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new IllegalActionException("there is no company by the id : " + id));
		companyRepository.delete(company);
		System.out.println("company: " + company.getName() + " was deleted succesfully");
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public void addCustomer(Customer customer) throws IllegalActionException, SaveException {
		if (customerRepository.existsByEmail(customer.getEmail())) {
			throw new IllegalActionException("Error , email: " + customer.getEmail()
					+ "already exists , try adding a customer with a different email");
		}
		try {
			customerRepository.saveAndFlush(customer);
		} catch (Exception e) {
			throw new SaveException(e.getMessage());
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws IllegalActionException, SaveException {
		Customer toUpdate = customerRepository.findById(customer.getId()).orElseThrow(
				() -> new IllegalActionException("customer by the id: " + customer.getId() + "doesnt exist"));
		try {
			customerRepository.saveAndFlush(customer);
		} catch (Exception e) {
			throw new SaveException("The email you want is already taken (email is unique) , try with different attributes..");
		}
	}

	@Override
	public void deleteCustomer(int id) throws IllegalActionException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new IllegalActionException("the customer by the id: " + id + "doesnt exist"));
		customerRepository.delete(customer);

		System.out.println("customer " + customer.getFirstName() + " was deleted succesfully");
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getOneCustomer(int id) throws IllegalActionException {

		return customerRepository.findById(id).orElseThrow(
				() -> new IllegalActionException("No customer by the id: " + id + ", try a diferent one "));
	}

}
