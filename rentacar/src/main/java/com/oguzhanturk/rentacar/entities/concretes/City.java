package com.oguzhanturk.rentacar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "cities")
@Entity
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private int id;

	@Column(name = "city_name")
	private String cityName;

	@OneToMany(mappedBy = "city")
	private List<Car> cars;

	@OneToMany(mappedBy = "fromCity")
	private List<Rental> fromCityRentals;

	@OneToMany(mappedBy = "toCity")
	private List<Rental> toCityRentals;
}
