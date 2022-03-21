package com.oguzhanturk.rentacar.business.request.carAccident;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateCarAccidentRequest {

	@NotNull
	@Positive
	private int id;

	@NotNull
	private String description;

	@NotNull
	@Positive
	private int carId;
}