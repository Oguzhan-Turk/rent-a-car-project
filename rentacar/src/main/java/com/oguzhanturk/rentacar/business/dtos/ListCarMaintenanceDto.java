package com.oguzhanturk.rentacar.business.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarMaintenanceDto {

	private String maintenanceDescription;
	private LocalDateTime returnDate;

	private int carId;
}
