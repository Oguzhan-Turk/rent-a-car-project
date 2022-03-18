package com.oguzhanturk.rentacar.entities.concretes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.oguzhanturk.rentacar.entities.abstracts.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rentals")
@Entity
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rent_id")
	private int rentId;

	@Column(name = "rent_date")
	private LocalDate rentDate;

	@Column(name = "return_date")
	private LocalDate returnDate;

	@Column(name = "start_kilometer")
	private double startKilometer;

	@Column(name = "return_kilometer")
	private double returnKilometer;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customer;

	@OneToMany(mappedBy = "rental")
	private List<AdditionalService> orderedAdditionalServices;

	@ManyToOne
	@JoinColumn(name = "rent_city_id")
	private City rentCity;

	@ManyToOne
	@JoinColumn(name = "return_city_id")
	private City returnCity;

	@Column(name = "rental_daily_price")
	private BigDecimal rentalDailyPrice;

	@Column(name = "rental_total_price")
	private BigDecimal rentalTotalPrice;

	@OneToOne(mappedBy = "rental", cascade = CascadeType.ALL)
	private Invoice invoice;

}
