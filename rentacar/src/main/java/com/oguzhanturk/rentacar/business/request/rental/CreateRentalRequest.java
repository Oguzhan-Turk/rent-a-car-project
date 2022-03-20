package com.oguzhanturk.rentacar.business.request.rental;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	@Min(value = 1)
	private int carCarId;

	@Min(value = 1)
	private int customerId;

	@NotNull
	private LocalDate rentDate;

	@Min(value = 1)
	private int fromCityId;

	private List<Integer> additionalId;

}
