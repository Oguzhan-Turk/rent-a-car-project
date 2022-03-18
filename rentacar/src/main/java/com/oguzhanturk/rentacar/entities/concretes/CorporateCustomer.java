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
@Table(name = "corporate_customers")
public class CorporateCustomer extends Customer {

//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private int id;

	@Column(name = "corporate_name")
	private String corporateName;

	@Column(name = "corporate_tax_no", unique = true)
	private String taxNo;

}