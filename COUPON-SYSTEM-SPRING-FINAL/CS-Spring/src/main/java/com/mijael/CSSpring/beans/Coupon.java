package com.mijael.CSSpring.beans;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.mijael.CSSpring.enums.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "coupons")
@Scope("prototype")
public class Coupon {
	private static int counter = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;

	@Column
	private int companyId;

	@Enumerated(EnumType.ORDINAL)
	private CategoryType categoryId;

	@Column(nullable = false, unique = true, length = 25)
	private String title;

	@Column(nullable = false, length = 255)
	private String description;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate endDate;

	@Column(nullable = false)
	private int amount;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false, length = 255)
	private String image;

	@Singular
	@ManyToMany(cascade = CascadeType.DETACH)
	private List<Customer> customers;

	public static Coupon createNewCoupon(int companyId, CategoryType category) {
		return Coupon.builder().companyId(companyId).categoryId(category).title("NewTitle" + counter++)
				.description("description" + counter++).startDate(LocalDate.now()).endDate(LocalDate.now().plusDays(65))
				.amount(5).price(Math.random() * 40 + 1).image("image.jpg" + counter++).build();
	}

}
