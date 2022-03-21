package com.oguzhanturk.rentacar.business.abstracts;

import com.oguzhanturk.rentacar.business.request.payment.CreatePaymentRequest;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface PaymentService {
	public Result add(CreatePaymentRequest createPaymentRequest);
}
