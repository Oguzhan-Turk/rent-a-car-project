package com.oguzhanturk.rentacar.business.request.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

	private int invoiceNo;

	private LocalDate billingDate;

	private int rentalId;

//
//	private int invoiceNo;
//
//	private LocalDate billingDate;
//
//	private LocalDate rentalDate;
//
//	private int totalRentDay;
//
//	private BigDecimal totalPrice;
//
//	private int customerId;
//
//	private int rentalId;
}
