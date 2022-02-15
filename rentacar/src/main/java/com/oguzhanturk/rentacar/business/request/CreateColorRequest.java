package com.oguzhanturk.rentacar.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {

//	private int colorId;
	private String colorName;
}