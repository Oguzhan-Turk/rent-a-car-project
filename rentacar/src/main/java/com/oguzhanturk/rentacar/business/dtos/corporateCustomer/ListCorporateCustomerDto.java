package com.oguzhanturk.rentacar.business.dtos.corporateCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCorporateCustomerDto {

	private int customerId;
	private String corporateName;
	private String taxNo;
	private String email;
	private String password;
}
