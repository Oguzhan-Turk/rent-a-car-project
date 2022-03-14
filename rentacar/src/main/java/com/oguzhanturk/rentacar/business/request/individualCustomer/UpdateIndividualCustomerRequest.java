package com.oguzhanturk.rentacar.business.request.individualCustomer;

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
public class UpdateIndividualCustomerRequest {

	@Min(value = 1)
	private int id;
	
	@NotEmpty
	@Size(min = 11, max = 11)
	private String nationalIdentity;

	@NotEmpty
	@Size(min = 3, max = 50)
	private String firstname;

	@NotEmpty
	@Size(min = 2, max = 50)
	private String lastname;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String password;
}
