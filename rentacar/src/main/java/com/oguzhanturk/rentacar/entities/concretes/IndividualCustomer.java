package com.oguzhanturk.rentacar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.oguzhanturk.rentacar.entities.abstracts.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "individual_customers")
public class IndividualCustomer extends Customer {

//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private int id;

	@Column(name = "national_identity", unique = true)
	private String nationalIdentity;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;
}