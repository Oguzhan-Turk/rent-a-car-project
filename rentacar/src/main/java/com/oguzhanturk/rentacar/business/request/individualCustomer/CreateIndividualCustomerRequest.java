package com.oguzhanturk.rentacar.business.request.individualCustomer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

	@Size(min = 11, max = 11)
	private String nationalIdentity;

	@Size(min = 3, max = 50)
	private String firstname;

	@Size(min = 2, max = 50)
	private String lastname;

	@Email
	private String email;

	@NotEmpty
	private String password;
}
