package com.oguzhanturk.rentacar.entities.concretes;

import java.time.LocalDateTime;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_maintenances")
@Entity
public class CarMaintenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maintenance_id")
	private int maintenanceId;
	@Column(name = "maintenance_description")
	private String maintenanceDescription;
	@Column(name = "return_date")
	private LocalDateTime returnDate;
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

}
