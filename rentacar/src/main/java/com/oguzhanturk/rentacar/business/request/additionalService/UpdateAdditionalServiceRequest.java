package com.oguzhanturk.rentacar.business.request.additionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {

	private int additionalId;
	private String additionalServiceName;
	private String additionalServiceDescription;
	private int dailyPrice;
	private int rentalId;
}
