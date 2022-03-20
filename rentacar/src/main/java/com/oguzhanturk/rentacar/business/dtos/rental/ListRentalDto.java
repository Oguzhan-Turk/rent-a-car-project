package com.oguzhanturk.rentacar.business.dtos.rental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
	private String fromCityName;
	private String toCityName;
	private List<String> additionalServiceName;
	private BigDecimal rentalDailyPrice;
	private BigDecimal rentalTotalPrice;
	private int customerId;
	private int carCarId;

}
