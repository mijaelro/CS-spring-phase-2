package com.mijael.CSSpring.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.LoggInException;
import com.mijael.CSSpring.exceptions.SaveException;
import com.mijael.CSSpring.repos.CompanyRepository;
import com.mijael.CSSpring.repos.CouponRepository;
import com.mijael.CSSpring.repos.CustomerRepository;
import com.mijael.CSSpring.services.CompanyService;

@Service
@Scope("prototype")
public class CompanyServiceImpl extends ClientServiceImpl implements CompanyService {

	private int companyId;

	public CompanyServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
	}

	@Override
	public boolean logIn(String email, String password) throws LoggInException {
		boolean isLoggedIn = companyRepository.existsByEmailAndPassword(email, password);
		if (isLoggedIn == true) {
			companyId = companyRepository.findByEmailAndPassword(email, password).getId();
		} else {
			throw new LoggInException("Error, Unable to logg in.. try again ");
		}
		System.out.println("the company id is : " + companyId);
		return isLoggedIn;
	}

	@Override
	public void addCoupon(Coupon coupon) throws IllegalActionException, SaveException {
		if (couponRepository.existsByTitle(coupon.getTitle())) {
			throw new IllegalActionException(
					String.format("coupon with the title : %s already exists", coupon.getTitle()));
		}
		try {
			couponRepository.saveAndFlush(coupon);
		} catch (Exception e) {
			throw new SaveException(e.getMessage());
		}

	}
	
	@Override
	public void updateCoupon(Coupon coupon) throws IllegalActionException, SaveException {
		Coupon toUpdate = couponRepository.findById(coupon.getId()).orElseThrow(
				() -> new IllegalActionException("Error, the coupon by the id: " + coupon.getId() + "doesnt exist"));
		try {
			couponRepository.saveAndFlush(coupon);
		} catch (Exception e) {
			throw new SaveException("the title you requested for the coupon already exists (title is unique),try with different attributes...");

		}
	}


	@Override
	public void deleteCoupon(int id) throws IllegalActionException {
		Coupon coupon = couponRepository.findById(id)
				.orElseThrow(() -> new IllegalActionException("Error, there is no coupon by the id: " + id));
		couponRepository.delete(coupon);

		System.out.println("coupon: " + coupon.getTitle() + "was deleted succesfully");
	}

	@Override
	public Coupon getOneCoupon(int id) throws IllegalActionException {
		return couponRepository.findById(id).orElseThrow(() -> new IllegalActionException(
				"The coupon by the id: " + id + "doesnt exists, try a diferent id..."));
	}

	
	@Override
	public Company getCompanyDetails() throws IllegalActionException {
		return companyRepository.findById(companyId)
				.orElseThrow(() -> new IllegalActionException("company by the id : " + companyId + " doesnt exists"));
	}

	@Override
	public List<Coupon> getCompanyCoupons() {
		return couponRepository.findCouponsByCompanyId(companyId);
	}

	@Override
	public List<Coupon> getCompanyCoupons(CategoryType category) {
		return couponRepository.findByCategoryIdAndCompanyId(category, companyId);
	}

	@Override
	public List<Coupon> getCompanyCoupons(double maxPrice) {
		return couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
	}

}
