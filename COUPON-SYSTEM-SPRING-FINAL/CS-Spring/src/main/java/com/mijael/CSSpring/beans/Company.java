package com.mijael.CSSpring.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

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
@Scope("prototype")
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;

	@Column(unique = true, nullable = false, length = 25)
	private String name;

	@Column(unique = true, nullable = false, length = 80)
	private String email;

	@Column(nullable = false, length = 25)
	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	@Singular
	private List<Coupon> coupons;

}
