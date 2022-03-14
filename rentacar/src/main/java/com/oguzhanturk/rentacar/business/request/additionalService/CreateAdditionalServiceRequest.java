package com.oguzhanturk.rentacar.business.request.additionalService;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {

	private String name;
	private String description;
	private BigDecimal dailyPrice;
//	private int rentalId;
}
