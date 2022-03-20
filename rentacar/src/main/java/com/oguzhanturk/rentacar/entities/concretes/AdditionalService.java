package com.oguzhanturk.rentacar.entities.concretes;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "additional_service")
public class AdditionalService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "additional_service_id")
	private int additionalServiceId;

	@Column(name = "additional_service_name")
	private String additionalServiceName;

	@Column(name = "additional_service_description")
	private String additionalServiceDescription;

	@Column(name = "additional_service_daily_price")
	private BigDecimal additionalServiceDailyPrice;

	@ManyToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;
}
