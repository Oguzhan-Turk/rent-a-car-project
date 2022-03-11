package com.oguzhanturk.rentacar.business.request;

import java.time.LocalDate;

import javax.validation.constraints.Min;
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
	private LocalDate returnDate;

//	@Min(value = 1, message = "Car id should be positive integer")
//	private int carId;

//	@Min(value = 1, message = "Customer id should be positive integer")
//	private int customerId;

}
