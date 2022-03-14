package com.oguzhanturk.rentacar.business.dtos.corporateCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerDto {

	private int id;
	private String corporateName;
	private String taxNo;
	private String email;
	private String password;

}
