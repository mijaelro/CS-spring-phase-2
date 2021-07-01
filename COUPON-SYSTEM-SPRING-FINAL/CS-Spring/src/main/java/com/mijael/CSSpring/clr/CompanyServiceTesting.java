package com.mijael.CSSpring.clr;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.impl.CompanyServiceImpl;
import com.mijael.CSSpring.services.LoginManagerService;
import com.mijael.CSSpring.utils.ArtUtils;
import com.mijael.CSSpring.utils.TestUtil;

import lombok.RequiredArgsConstructor;

@Component
@Order(3)
@RequiredArgsConstructor
public class CompanyServiceTesting implements CommandLineRunner {

	private final LoginManagerService loginManagerService;

	@Override
	public void run(String... args) throws Exception {

		System.out.println(ArtUtils.COMPANY_SERVICE_TESTING);

		TestUtil.testInfo("Logg in [GOOD]");

		CompanyServiceImpl companyServiceImpl = (CompanyServiceImpl) loginManagerService.logIn("apple@apple.com",
				"1234", ClientType.COMPANY);
		System.out.println(companyServiceImpl);

		// TestUtil.testInfo("Logg in [BAD]");

		// System.out.println(loginManagerService.logIn("apple@apple.comm", "1234m",
		// ClientType.COMPANY));

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("addCoupon [GOOD]");
		Coupon c1 = Coupon.builder().amount(2).categoryId(CategoryType.ELECTRICITY).companyId(2).description("desc")
				.endDate(LocalDate.now().plusYears(1)).startDate(LocalDate.now()).image("imag").price(10).title("newC")
				.build();
		Coupon c2 = Coupon.builder().amount(2).categoryId(CategoryType.ELECTRICITY).companyId(2).description("descrip")
				.endDate(LocalDate.now().plusYears(1)).startDate(LocalDate.now()).image("image").price(102)
				.title("newC").build();

		companyServiceImpl.addCoupon(c1);
		System.out.println(c1);

		// TestUtil.testInfo("addCoupon [BAD->title already exist]");

		// companyServiceImpl.addCoupon(c2);
		// System.out.println(c2);

		System.out.println("--------------------------------------------------------------------------------");
		TestUtil.testInfo("updateCoupon [GOOD]");
		c1.setAmount(3);
		c1.setDescription("oh what a description");
		c1.setEndDate(LocalDate.now().minusDays(1));
		c1.setStartDate(LocalDate.now());
		c1.setImage("imageNew.jpg");
		c1.setPrice(200.3);
		c1.setTitle("NewTitle111");
		companyServiceImpl.updateCoupon(c1);
		System.out.println("updated coupon---->");
		System.out.println(c1);

		// TestUtil.testInfo("updateCoupon [BAD-coupon doesnt exist]");

		// c2.setTitle("lets try it out");
		// companyServiceImpl.updateCoupon(c2);

		// TestUtil.testInfo("updateCoupon [BAD-coupon's title already exist]");

		// c1.setTitle("NewTitle1");
		// companyServiceImpl.updateCoupon(c1);
		// System.out.println(c1);

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("getOneCoupon [GOOD]");
		System.out.println(companyServiceImpl.getOneCoupon(5));

		// TestUtil.testInfo("getOneCoupon [BAD-> coupon doesnt exist]");

		// System.out.println(companyServiceImpl.getOneCoupon(0));

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("deleteCoupon [GOOD]");
		companyServiceImpl.deleteCoupon(7);
		// TestUtil.testInfo("deleteCoupon [BAD]");

		// companyServiceImpl.deleteCoupon(0);

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("getCompanyDetails");
		System.out.println(companyServiceImpl.getCompanyDetails());

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("getCompanyCoupons");
		companyServiceImpl.getCompanyCoupons().forEach(System.out::println);

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("getCompanyCouponsByCategory[GOOD]");
		companyServiceImpl.getCompanyCoupons(CategoryType.RESTAURANT).forEach(System.out::println);

		System.out.println("--------------------------------------------------------------------------------");

		TestUtil.testInfo("getCompanyCouponsByMaxPrice");
		companyServiceImpl.getCompanyCoupons(38.00).forEach(System.out::println);

	}

}
