package com.mijael.CSSpring.clr;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.services.AdminService;
import com.mijael.CSSpring.services.LoginManagerService;
import com.mijael.CSSpring.utils.ArtUtils;
import com.mijael.CSSpring.utils.TestUtil;

import lombok.RequiredArgsConstructor;

@Order(2)
@Component
@RequiredArgsConstructor
public class AdminServiceTesting implements CommandLineRunner {

	private final LoginManagerService loginManagerService;

	@Override
	public void run(String... args) throws Exception {

		System.out.println(ArtUtils.ADMIN_SERVICE_TESTING);

		TestUtil.testInfo("Logg-In [GOOD]");

		System.out.println(loginManagerService.logIn("admin@admin.com", "admin", ClientType.ADMINISTRATOR));

		AdminService adminServiceImpl = (AdminService) loginManagerService.logIn("admin@admin.com", "admin",
				ClientType.ADMINISTRATOR);

		// TestUtil.testInfo("Logg-In [BAD -> unable to loggin]");

		// System.out.println(loginManagerService.logIn("moshe@gmail.com",
		// "1234",ClientType.ADMINISTRATOR));

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("addCompany [GOOD]");

		Company company = Company.builder().name("someComp").email("someEmail.com").password("123").build();
		Company company1 = Company.builder().name("someComp").email("seEmail.com").password("123").build();
		Company company3 = Company.builder().name("sComp").email("someEmail.com").password("123").build();
		Company company4 = Company.builder().name("Comp").email("Emaiawl.com").password("123").build();

		adminServiceImpl.addCompany(company);
		adminServiceImpl.addCompany(company4);
		System.out.println("admin companies after adding..");
		adminServiceImpl.getAllCompanies().forEach(System.out::println);

		// TestUtil.testInfo("addCompany [BAD -> Duplicate Name]");

		// adminServiceImpl.addCompany(company1);
		// TestUtil.testInfo("addCompany [BAD -> Duplicate email]");
		// adminServiceImpl.addCompany(company3);

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("getOneCompany [GOOD]");

		System.out.println(adminServiceImpl.getOneCompany(1));

		// TestUtil.testInfo("getOneCompany [BAD] -> company doesnt exist");

		// System.out.println(adminServiceImpl.getOneCompany(0));

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("updateCompany [GOOD]");

		company.setEmail("newEmail@.com");
		company.setPassword("11111");
		adminServiceImpl.updateCompany(company);
		System.out.println("updated company------>");
		System.out.println(company);

		//TestUtil.testInfo("updateCompany [BAD-Email already exists]");
		//company4.setEmail("apple@apple.com");
		//adminServiceImpl.updateCompany(company4);
		
		//TestUtil.testInfo("updateCompany [BAD- Name already exists]");
		//company4.setName("Apple");
		//adminServiceImpl.updateCompany(company4);
		
		// TestUtil.testInfo("updateCompany [BAD]->company doesnt exist");

		// company1.setEmail("newEmail.com");
		// adminServiceImpl.updateCompany(company1);

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("DeleteCompany[GOOD]");

		adminServiceImpl.DeleteCompany(5);

		// TestUtil.testInfo("DeleteCompany [BAD]->company doesnt exist");

		// adminServiceImpl.DeleteCompany(0);

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("getAllCompanies ");

		adminServiceImpl.getAllCompanies().forEach(System.out::println);

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("getting single company [GOOD]");

		System.out.println(adminServiceImpl.getOneCompany(1));

		// TestUtil.testInfo("getting single company [BAD->company doesnt exist]");

		// System.out.println(adminServiceImpl.getOneCompany(5));

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("addCustomer [GOOD]");

		Customer customer = Customer.builder().firstName("Alan").lastName("Remba").email("alanr@gmail.com")
				.password("1212").build();
		Customer customer2 = Customer.builder().firstName("yoniii").lastName("Renata").email("alanr@gmail.com")
				.password("121212").build();
		adminServiceImpl.addCustomer(customer);
		System.out.println(customer);

		// TestUtil.testInfo("addCustomer [BAD]-> customer's email already exist");

		// adminServiceImpl.addCustomer(customer2);

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("updateCustomer [GOOD]");

		customer.setEmail("rene@gmail.com");
		customer.setFirstName("Rene");
		customer.setLastName("Renauar");
		customer.setPassword("12121212");
		adminServiceImpl.updateCustomer(customer);
		System.out.println(customer);

		//TestUtil.testInfo("updateCustomer [BAD->Email already exists]");

		//customer.setEmail("shira@hotmail.com");
		//adminServiceImpl.updateCustomer(customer);
		
		// TestUtil.testInfo("updateCustomer [BAD->customer doesnt exist]");

		// customer2.setFirstName("moshe");
		// adminServiceImpl.updateCustomer(customer2);
		// System.out.println(customer2);

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("deleteCustomer [GOOD]");
		adminServiceImpl.deleteCustomer(5);
		
		//TestUtil.testInfo("deleteCustomer [BAD->customer doesnt exist]");
		
		//adminServiceImpl.deleteCustomer(0);

		System.out.println("--------------------------------------------------------------------------------");
		
		TestUtil.testInfo("getAllCustomers");
		System.out.println(adminServiceImpl.getAllCustomers());

		System.out.println("--------------------------------------------------------------------------------");
		
		TestUtil.testInfo("getOneCustomer [GOOD]");
		System.out.println(adminServiceImpl.getOneCustomer(1));
		
		// TestUtil.testInfo("getOneCustomer [BAD]");
		
		// System.out.println(adminServiceImpl.getOneCustomer(0));

	}

}
