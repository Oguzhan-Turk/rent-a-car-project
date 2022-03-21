package com.oguzhanturk.rentacar.business.dtos.carAccident;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarAccidentDto {
	
	private int id;
	private String description;
	private int carId;

}
