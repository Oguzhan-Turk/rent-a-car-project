package com.oguzhanturk.rentacar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.oguzhanturk.rentacar.entities.abstracts.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "customer_id")
public class CorporateCustomer extends Customer {

	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "corporate_name")
	private String corporateName;

	@Column(name = "corporate_tax_no", unique = true)
	private String taxNo;

}