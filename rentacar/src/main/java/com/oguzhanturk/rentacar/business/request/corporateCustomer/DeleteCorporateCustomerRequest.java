package com.oguzhanturk.rentacar.business.request.corporateCustomer;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCorporateCustomerRequest {

	@Min(value = 1)
	private int id;
}
