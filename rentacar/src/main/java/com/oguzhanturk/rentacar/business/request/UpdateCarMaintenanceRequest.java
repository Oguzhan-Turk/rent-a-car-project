package com.oguzhanturk.rentacar.business.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {

	private int maintenanceId;
	private String maintenanceDescription;
	private LocalDateTime returnDate;
}
