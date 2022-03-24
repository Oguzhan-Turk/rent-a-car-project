package com.oguzhanturk.rentacar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit_cards")
@Entity
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credit_card_id")
	private int creditCardId;

	@Column(name = "owner_name")
	private String ownerName;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "cvv_number")
	private int cvvNumber;

	@OneToMany(mappedBy = "creditCard", fetch = FetchType.LAZY)
	private List<Payment> payments;
}
