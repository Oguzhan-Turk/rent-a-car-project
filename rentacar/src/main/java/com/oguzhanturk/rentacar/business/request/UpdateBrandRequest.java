package com.oguzhanturk.rentacar.business.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {

	@Min(value = 1, message = "Brand id should be positive integer")
	private int brandId;

	@NotEmpty(message = "Brand name should not be null or empty")
	@Size(min = 2, message = "Brand name should have at least 2 characters")
	private String brandName;
}
