package com.oguzhanturk.rentacar.business.request;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	@NotNull
	private LocalDateTime rentDate;

	@Min(value = 1, message = "Car id should be positive integer")
	private int carId;

	@Min(value = 1, message = "Customer id should be positive integer")
	private int customerId;
}