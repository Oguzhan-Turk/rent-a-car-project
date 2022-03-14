package com.oguzhanturk.rentacar.business.dtos.city;

import java.util.List;

import com.oguzhanturk.rentacar.entities.concretes.Car;
import com.oguzhanturk.rentacar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCityDto {

	private int id;
	private String cityName;
	private List<Car> cars;
	private List<Rental> rentCityRentals;
	private List<Rental> returnCityRentals;
}
