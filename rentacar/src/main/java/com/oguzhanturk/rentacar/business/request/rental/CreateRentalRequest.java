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

	private LocalDate rentDate;

	@Min(value = 1)
	private int carId;

	@Min(value = 1)
	private int userId;

	@Min(value = 1)
	private int rentCityId;

//	@Min(value = 1)
//	private int returnCityId;

	private List<Integer> additionalServiceId;

}
