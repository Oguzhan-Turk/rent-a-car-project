package com.oguzhanturk.rentacar.business.dtos.additionalService;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceDto {

	private int additionalId;
	private String additionalServiceName;
	private String additionalServiceDescription;
	private BigDecimal additionalServiceDailyPrice;
}
