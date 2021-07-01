package com.mijael.CSSpring.job;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.repos.CouponRepository;

import lombok.RequiredArgsConstructor;
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CouponExpirationDailyJob {

	private final CouponRepository couponRepository;

	@Scheduled(initialDelay = 1000*60*60, fixedRate =1000*60*60*24)
	public void CouponExpirationDailyJob() {
		System.out.println(("RUNNING DAILY TASK FOR COUPON EXPIRATION CHECK "));
		List<Coupon> couponList = couponRepository.findCouponsByEndDate(Date.valueOf(LocalDate.now()));
		if (couponList != null) {
			for (Coupon c : couponList) {
				System.out.println(("the Coupon" + c + " has already expired , deleting it and its purchase history"));
				couponRepository.delete(c);
			}
		}

	}
}
//convert into functional programming.. 