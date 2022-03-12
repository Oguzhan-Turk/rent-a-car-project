package com.oguzhanturk.rentacar.business.request.additionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {

	private String additionalServiceName;
	private int dailyPrice;
	private int rentalId;
}
