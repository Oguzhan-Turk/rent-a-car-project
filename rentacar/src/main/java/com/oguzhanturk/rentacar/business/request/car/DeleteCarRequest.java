package com.oguzhanturk.rentacar.business.request.car;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarRequest {

	@Min(value = 1, message = "Car id should be positive integer")
	private int carId;

}
