package com.oguzhanturk.rentacar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.PaymentService;
import com.oguzhanturk.rentacar.business.abstracts.PosService;
import com.oguzhanturk.rentacar.business.request.payment.CreatePaymentRequest;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;

@Service
public class PosAdapter implements PosService {
	PaymentService paymentService;

	@Autowired
	public PosAdapter(@Lazy PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	public Result payment(CreatePaymentRequest createPaymentRequest) {
		String last4digits = createPaymentRequest.getCardNumber().substring(12);
		return new SuccessResult();
	}
}
