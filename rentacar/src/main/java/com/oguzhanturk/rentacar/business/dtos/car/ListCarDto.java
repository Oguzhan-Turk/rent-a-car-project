package com.oguzhanturk.rentacar.business.dtos.car;

import java.math.BigDecimal;

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
	private double kilometer;

	private String brandName;
	private String colorName;

}
