package com.oguzhanturk.rentacar.business.dtos.rental;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {

	private int rentId;
	private LocalDate rentDate;
	private LocalDate returnDate;

	private int carId;

}
