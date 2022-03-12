package com.oguzhanturk.rentacar.business.request.rental;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRentalRequest {

	@Min(value = 1, message = "Rent id should be positive integer")
	private int rentId;
}
