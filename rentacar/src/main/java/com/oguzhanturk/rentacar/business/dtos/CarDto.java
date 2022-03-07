package com.oguzhanturk.rentacar.business.dtos;

import java.math.BigDecimal;
import java.util.List;

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
