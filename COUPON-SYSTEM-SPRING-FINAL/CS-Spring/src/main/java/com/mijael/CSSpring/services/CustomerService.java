package com.mijael.CSSpring.services;

import java.util.List;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.PurchaseException;

public interface CustomerService {

	void purchaseCoupon(Coupon coupon) throws IllegalActionException, PurchaseException;

	Coupon getSingleCoupon(int couponId) throws IllegalActionException;

	Customer getCustomersDetails() throws IllegalActionException;

	List<Coupon> getCustomerCoupons();

	List<Coupon> getCustomerCoupons(CategoryType category) throws IllegalActionException;

	List<Coupon> getCustomerCoupons(double maxPrice);

}
