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
	@Column(name = "invoice_id")
	private int invoiceId;

	@Column(name = "billing_date")
	private LocalDate billingDate;

	@Column(name = "rent_date")
	private LocalDate rentDate;

	@Column(name = "return_date")
	private LocalDate returnDate;

	@Column(name = "total_rent_day")
	private long totalRentDay;

	@Column(name = "total_price")
	private double totalPrice;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customer;

	@OneToOne()
	@JoinColumn(name = "rent_id")
	private Rental rental;

	@OneToOne(mappedBy = "invoice")
	private Payment payment;

}
