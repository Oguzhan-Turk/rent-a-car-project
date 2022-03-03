package com.oguzhanturk.rentacar.business.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

	private int carId;
	private BigDecimal dailyPrice;
	private int modelYear;
	private String description;
	private int brandId;
	private int colorId;

}
