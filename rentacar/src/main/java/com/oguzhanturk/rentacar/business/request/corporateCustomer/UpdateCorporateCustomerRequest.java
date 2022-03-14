package com.oguzhanturk.rentacar.business.request.corporateCustomer;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {

	@Min(value = 1)
	private int id;

	@Size(min = 2, max = 50)
	private String corporateName;

	@Size(min = 10, max = 10)
	private String taxNo;

	@Email
	private String email;

	@NotEmpty
	private String password;
}
