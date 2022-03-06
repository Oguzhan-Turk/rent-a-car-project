package com.oguzhanturk.rentacar.business.request;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBrandRequest {
	
	@Min(value = 1, message = "Brand id should be positive integer")
	private int brandId;

}
