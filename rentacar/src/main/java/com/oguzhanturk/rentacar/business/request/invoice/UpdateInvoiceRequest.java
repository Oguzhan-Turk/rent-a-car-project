package com.oguzhanturk.rentacar.business.request.invoice;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

	private int invoiceId;

	private int customerId;

	private LocalDate billingDate;

	private int rentId;

}
