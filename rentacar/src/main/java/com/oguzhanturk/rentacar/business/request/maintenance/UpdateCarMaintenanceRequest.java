package com.oguzhanturk.rentacar.business.request.maintenance;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {

	@Min(value = 1, message = "Maintenance id should be positive integer")
	private int maintenanceId;
	
//	@NotEmpty(message = "Maintenance Description should not be null or empty")
//	@Size(min = 3, message = "Maintenance Description should have at least 3 characters")
//	private String maintenanceDescription;
	
	@NotNull
	private LocalDate returnDate;
}
