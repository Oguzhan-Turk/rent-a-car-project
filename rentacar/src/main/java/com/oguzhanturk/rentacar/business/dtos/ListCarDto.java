package com.oguzhanturk.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {

	private int carId;
	private double dailyPrice;
	private int modelYear;
	private String description;

	private String brandName;
	private String colorName;
}
