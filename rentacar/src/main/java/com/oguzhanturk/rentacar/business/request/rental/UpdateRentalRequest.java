package com.oguzhanturk.rentacar.business.request.rental;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

	@Min(value = 1, message = "Rent id should be positive integer")
	private int rentId;

	private LocalDate rentDate;

	private LocalDate returnDate;
	
	private double startkilometer;

	private double returnKilometer;

	@Min(value = 1)
	private int carId;

//	@Min(value = 1)
	private int userId;

	@Min(value = 1)
	private int rentCityId;

	@Min(value = 1)
	private int returnCityId;

	private List<Integer> additionalServiceId;

}
