package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.PaymentService;
import com.oguzhanturk.rentacar.business.abstracts.PosService;
import com.oguzhanturk.rentacar.business.dtos.payment.ListPaymentDto;
import com.oguzhanturk.rentacar.business.request.payment.CreatePaymentRequest;
import com.oguzhanturk.rentacar.business.request.payment.DeletePaymentRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
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

	@Override
	public Result delete(DeletePaymentRequest deletePaymentRequest) throws BusinessException {

		checkIfPaymentExist(deletePaymentRequest.getPaymentId());
		paymentDao.deleteById(deletePaymentRequest.getPaymentId());
		return new SuccessResult();
	}

	@Override
	public DataResult<List<ListPaymentDto>> getAll() {

		List<Payment> result = this.paymentDao.findAll();
		List<ListPaymentDto> response = result.stream()
				.map(payment -> this.modelMapperService.forDto().map(payment, ListPaymentDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListPaymentDto>>(response);
	}
	
	private void toSendPosService(CreatePaymentRequest createPaymentRequest) {
		posService.payment(createPaymentRequest);
	}

	private void checkIfPaymentExist(int paymentId) throws BusinessException {
		if (!paymentDao.existsById(paymentId)) {
			throw new BusinessException("Payment does not exist!");
		}
	}

}
