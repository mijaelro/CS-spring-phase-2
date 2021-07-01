package com.mijael.CSSpring.clr;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.repos.CompanyRepository;
import com.mijael.CSSpring.repos.CustomerRepository;
import com.mijael.CSSpring.utils.ArtUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(1)
public class InnitData implements CommandLineRunner {
	private final CompanyRepository companyRepository;
	private final CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {

		System.out.println(ArtUtils.INNIT_DATA);

		System.out.println(ArtUtils.COUPON);
		System.out.println("Adding coupons");

		Coupon c = Coupon.createNewCoupon(1, CategoryType.ELECTRICITY);
		Coupon c1 = Coupon.createNewCoupon(1, CategoryType.FOOD);
		Coupon c2 = Coupon.createNewCoupon(1, CategoryType.LIFE_STYLE);
		Coupon c3 = Coupon.createNewCoupon(2, CategoryType.RESTAURANT);
		Coupon c4 = Coupon.createNewCoupon(2, CategoryType.VACATION);
		Coupon c5 = Coupon.createNewCoupon(2, CategoryType.ELECTRICITY);
		Coupon c6 = Coupon.createNewCoupon(3, CategoryType.FOOD);
		Coupon c7 = Coupon.createNewCoupon(4, CategoryType.LIFE_STYLE);
		Coupon c8 = Coupon.createNewCoupon(5, CategoryType.LIFE_STYLE);

		c1.setEndDate(LocalDate.now().minusDays(1));
		c2.setAmount(0);

		System.out.println(c);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c5);
		System.out.println(c6);
		System.out.println(c7);
		System.out.println(c8);

		System.out.println(
				"------------------------------------------------------------------------------------------------------");

		Company linux = Company.builder().email("linux@linux.com").name("linux").password("1234")
				.coupons(Arrays.asList(c, c1, c2)).build();

		Company apple = Company.builder().email("apple@apple.com").name("Apple").password("1234")
				.coupons(Arrays.asList(c3, c4, c5)).build();

		Company matrix = Company.builder().email("matrix@matrix.com").name("Matrix").password("1234")
				.coupons(Arrays.asList(c6)).build();

		Company dell = Company.builder().email("dell@dell.com").name("Dell").password("1234").coupons(Arrays.asList(c7))
				.build();

		Company linuxx = Company.builder().email("linuxx@linuxx.com").name("linuxx").password("1234")
				.coupons(Arrays.asList(c8)).build();

		companyRepository.saveAll(Arrays.asList(linux, apple, matrix, dell, linuxx));

		System.out.println(ArtUtils.COMPANY);
		System.out.println("adding companies-->");
		System.out.println(linux);
		System.out.println(linuxx);
		System.out.println(apple);
		System.out.println(matrix);
		System.out.println(dell);

		System.out.println(
				"------------------------------------------------------------------------------------------------------");

		System.out.println(ArtUtils.CUSTOMER);
		System.out.println("adding Customers--->");

		Customer cus1 = Customer.builder().email("moishe@gmail.com").firstName("moshe").lastName("moshiko")
				.password("1234").build();

		Customer cus2 = Customer.builder().email("yoni@gmail.com").firstName("yoni").lastName("eyov").password("1234")
				.build();

		Customer cus3 = Customer.builder().email("shira@hotmail.com").firstName("shira").lastName("cohen")
				.password("1234").build();

		Customer cus4 = Customer.builder().email("shisra@hotmail.com").firstName("shiras").lastName("cohens")
				.password("1234").build();

		customerRepository.saveAll(Arrays.asList(cus1, cus2, cus3, cus4));
		System.out.println(cus1);
		System.out.println(cus2);
		System.out.println(cus3);
		System.out.println(cus4);

		System.out.println(
				"------------------------------------------------------------------------------------------------------");
	}

}
