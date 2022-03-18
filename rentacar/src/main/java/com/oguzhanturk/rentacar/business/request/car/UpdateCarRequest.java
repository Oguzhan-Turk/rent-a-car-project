package com.oguzhanturk.rentacar.business.request.car;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

	@Min(value = 1, message = "Car id should be positive integer")
	private int carId;

	@NotNull(message = "Daily price should not be null")
	@Positive(message = "Daily price should be positive")
	private BigDecimal dailyPrice;

	@Min(value = 1900, message = "Model year should not be least 1900 or null")
	private int modelYear;

	@NotEmpty(message = "Description should not be null or empty")
	@Size(min = 2, message = "Description should have at least 2 characters")
	private String description;

	@Min(value = 1, message = "Brand id should be positive integer")
	private int brandId;

	@Min(value = 1, message = "Color id should be positive integer")
	private int colorId;

	@Min(value = 0)
	private double kilometer;

}
