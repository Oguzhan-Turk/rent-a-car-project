package com.oguzhanturk.rentacar.business.request.rental;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	@NotEmpty
	private LocalDate rentDate;

	@NotEmpty
	@Min(value = 1)
	private int carId;

	@NotEmpty
	@Min(value = 1)
	private int customerId;

	@NotEmpty
	@Min(value = 1)
	private int rentCityId;

	@NotEmpty
	@Min(value = 1)
	private int returnCityId;

	private List<Integer> additionalServiceId;

}
