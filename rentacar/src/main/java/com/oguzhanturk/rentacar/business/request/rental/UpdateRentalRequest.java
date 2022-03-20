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

	@NotNull
	private LocalDate rentDate;

	@NotNull
	private LocalDate returnDate;

	@Min(value = 1)
	private int carCarId;

	@Min(value = 1)
	private int customerId;


	@Min(value = 1)
	private double returnKilometer;

	@NotNull
	@Min(value = 1)
	private int fromCityId;

	@Min(value = 1)
	private int toCityId;

	private List<Integer> additionalId;

}
