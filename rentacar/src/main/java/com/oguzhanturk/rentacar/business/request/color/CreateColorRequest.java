package com.oguzhanturk.rentacar.business.request.color;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {

	@NotEmpty(message = "Color name should not be null or empty")
	@Size(min = 2, message = "Color name have at least 2 characters")
	private String colorName;
}