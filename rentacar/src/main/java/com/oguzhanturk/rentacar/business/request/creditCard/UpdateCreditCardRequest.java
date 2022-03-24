package com.oguzhanturk.rentacar.business.request.creditCard;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreditCardRequest {

	private int creditCardId;

	private String ownerName;

	private String cardNumber;

	private int cvvNumber;

	private List<Integer> paymentId;

}
