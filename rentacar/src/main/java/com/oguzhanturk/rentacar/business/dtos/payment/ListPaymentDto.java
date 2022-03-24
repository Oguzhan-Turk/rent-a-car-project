package com.oguzhanturk.rentacar.business.dtos.payment;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPaymentDto {
	
	private int paymentId;
	
	private String bankName;
	
	private String cardNumber;
	
	private String cardCvv;
	
	private String cardOwnerName;
	
	private LocalDate cardExpirationDate;
	
	private int rentalRentId;
	
	private int invoiceInvoiceNo;

}
