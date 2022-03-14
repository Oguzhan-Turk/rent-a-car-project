package com.oguzhanturk.rentacar.business.dtos.rental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.additionalService.ListAdditionalServiceDto;

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
	private String rentCityName;
	private String returnCityName;
	private List<ListAdditionalServiceDto> additionalServices;
	private BigDecimal rentalDailyPrice;
	private int customerId;
	private int carId;

}
