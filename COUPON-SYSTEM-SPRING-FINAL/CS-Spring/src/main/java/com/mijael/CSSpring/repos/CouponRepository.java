package com.mijael.CSSpring.repos;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.enums.CategoryType;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	boolean existsByTitle(String title);

	List<Coupon> findCouponsByCompanyId(int companyId);

	Coupon findCouponByTitle(String title);

	List<Coupon> findByCategoryIdAndCompanyId(CategoryType category, int companyId);

	List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double price);

	@Query(value = "SELECT * FROM COUPONS where end_date < ?1", nativeQuery = true)
	List<Coupon> findCouponsByEndDate(Date date);

	List<Coupon> findCouponByCustomers(Customer customer);

}
