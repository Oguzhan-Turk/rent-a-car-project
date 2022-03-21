package com.oguzhanturk.rentacar.entities.concretes;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payments")
@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_id")
	private int paymentId;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name="card_owner_name")
	private String cardOwnerName;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="card_cvv")
	private int cardCvv;
	
	@Column(name = "card_expiration_date")
	private LocalDate cardExpirationDate;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@Column(name = "total_payment")
	private double totalPayment;
	
	@ManyToOne()
	@JoinColumn(name = "rent_id")
	private Rental rental;
	
	@OneToOne
	@JoinColumn(name="invoice_no")
	private Invoice invoice;
}
