package com.oguzhanturk.rentacar.business.request.additionalService;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {

	private String additionalServiceName;
	private String additionalServiceDescription;
	private double additionalServiceDailyPrice;
}
