package com.oguzhanturk.rentacar.business.dtos.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInvoiceDto {

	private int invoiceId;

	private LocalDate billingDate;

	private LocalDate rentDate;

	private LocalDate returnDate;

	private int totalRentDay;

	private double totalPrice;

	private int customerId;

	private int rentId;
}
