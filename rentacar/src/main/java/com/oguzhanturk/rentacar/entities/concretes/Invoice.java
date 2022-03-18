package com.oguzhanturk.rentacar.entities.concretes;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.oguzhanturk.rentacar.entities.abstracts.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Invoices")
@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_no")
	private int invoiceNo;

	@Column(name = "billing_date")
	private LocalDate billingDate;

//	@Column(name = "rental_date")
//	private LocalDate rentalDate;

	@Column(name = "total_rent_day")
	private long totalRentDay;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customer;

	@OneToOne()
	@JoinColumn(name = "rent_id")
	private Rental rental;

}
