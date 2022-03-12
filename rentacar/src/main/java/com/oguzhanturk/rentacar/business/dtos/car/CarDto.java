package com.oguzhanturk.rentacar.business.dtos.car;

import java.math.BigDecimal;
import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.rental.RentalDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

	private int carId;
	private BigDecimal dailyPrice;
	private int modelYear;
	private String description;

	private String brandName;
	private String colorName;
	private List<RentalDto> rentalDtos;
}
