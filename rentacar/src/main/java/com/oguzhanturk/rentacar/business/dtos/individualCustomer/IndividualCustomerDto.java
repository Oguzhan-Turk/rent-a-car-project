package com.oguzhanturk.rentacar.business.dtos.individualCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {

	private int customerId;
	private String nationalIdentity;
	private String firstname;
	private String lastname;
	private String email;
	private String password;

}
