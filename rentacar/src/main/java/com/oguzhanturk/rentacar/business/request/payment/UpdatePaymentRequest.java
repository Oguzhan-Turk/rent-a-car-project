package com.oguzhanturk.rentacar.business.request.payment;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {

	@NotNull
	private int paymentId;

	@NotNull
	private int invoiceId;

	private int creditCardId;

}
