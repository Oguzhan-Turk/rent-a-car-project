package com.oguzhanturk.rentacar.entities.abstracts;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.oguzhanturk.rentacar.entities.concretes.Invoice;
import com.oguzhanturk.rentacar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@Entity
//@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "userId")
public abstract class Customer extends User {

//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "customer_id", insertable = false, updatable = false)
//	private int customerId;

	@OneToMany(mappedBy = "customer")
	private List<Rental> rentals;

	@OneToMany(mappedBy = "customer")
	private List<Invoice> invoices;

}
