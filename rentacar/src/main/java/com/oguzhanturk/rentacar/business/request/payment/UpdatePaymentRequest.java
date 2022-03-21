package com.oguzhanturk.rentacar.business.request.payment;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {

	@NotNull
	@Positive
	private int paymentId;

	@NotNull
	@Size(min = 3, max = 50)
	private String bankName;

	@NotNull
	@Size(min = 16, max = 16)
	private String cardNumber;

	@NotNull
	@Size(min = 3, max = 3)
	private String cardCvv;

	@NotNull
	@Size(min = 3, max = 100)
	private String cardOwnerName;

	@NotNull
	private LocalDate cardExpirationDate;

	@NotNull
	private LocalDate paymentDate;

	@NotNull
	private int rentId;

}
