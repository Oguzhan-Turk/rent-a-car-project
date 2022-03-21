package com.oguzhanturk.rentacar.business.request.carAccident;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarAccidentRequest {

	@NotNull
	private int id;

}