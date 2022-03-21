package com.oguzhanturk.rentacar.business.concretes;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.PaymentService;
import com.oguzhanturk.rentacar.business.abstracts.PosService;
import com.oguzhanturk.rentacar.business.request.payment.CreatePaymentRequest;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.PaymentDao;
import com.oguzhanturk.rentacar.entities.concretes.Payment;

@Service
public class PaymentManager implements PaymentService {

	PosService posService;
	ModelMapperService modelMapperService;
	PaymentDao paymentDao;

	@Autowired
	public PaymentManager(@Lazy PosService posService, ModelMapperService modelMapperService, PaymentDao paymentDao) {
		this.posService = posService;
		this.modelMapperService = modelMapperService;
		this.paymentDao = paymentDao;
	}

	@Override
	@Transactional
	public Result add(CreatePaymentRequest createPaymentRequest) {
		toSendPosService(createPaymentRequest);

		Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

		paymentDao.save(payment);

		return new SuccessResult();

	}

	private void toSendPosService(CreatePaymentRequest createPaymentRequest) {
		this.posService.payment(createPaymentRequest);
	}

}
