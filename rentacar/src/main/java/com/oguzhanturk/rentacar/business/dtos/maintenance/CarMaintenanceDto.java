package com.oguzhanturk.rentacar.business.dtos.maintenance;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceDto {

	private int maintenanceId;
	private String maintenanceDescription;
	private LocalDate returnDate;

	private int carId;
}
