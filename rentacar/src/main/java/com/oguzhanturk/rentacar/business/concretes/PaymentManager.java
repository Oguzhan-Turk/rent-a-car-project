package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.InvoiceService;
import com.oguzhanturk.rentacar.business.abstracts.PaymentService;
import com.oguzhanturk.rentacar.business.abstracts.PosService;
import com.oguzhanturk.rentacar.business.constants.Messages;
import com.oguzhanturk.rentacar.business.dtos.payment.ListPaymentDto;
import com.oguzhanturk.rentacar.business.dtos.payment.PaymentDto;
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
	InvoiceService invoiceService;

	@Autowired
	public PaymentManager(PosService posService, ModelMapperService modelMapperService, PaymentDao paymentDao,
			InvoiceService invoiceService) {
		this.posService = posService;
		this.modelMapperService = modelMapperService;
		this.paymentDao = paymentDao;
		this.invoiceService = invoiceService;
	}

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) throws BusinessException {
		toSendPosService(createPaymentRequest);

		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

		checkIfInvoiceExists(createPaymentRequest.getInvoiceId());

		this.paymentDao.save(payment);

		return new SuccessResult(Messages.PAYMENT_ADD);

	}

	@Override
	public Result delete(DeletePaymentRequest deletePaymentRequest) throws BusinessException {
		checkIfPaymentExist(deletePaymentRequest.getPaymentId());

		paymentDao.deleteById(deletePaymentRequest.getPaymentId());

		return new SuccessResult(Messages.PAYMENT_DELETE);
	}

	@Override
	public DataResult<List<ListPaymentDto>> getAll() {
		var result = this.paymentDao.findAll();

		List<ListPaymentDto> response = result.stream()
				.map(payment -> this.modelMapperService.forDto().map(payment, ListPaymentDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListPaymentDto>>(response, Messages.PAYMENT_LIST);
	}

	@Override
	public DataResult<PaymentDto> getById(int paymentId) throws BusinessException {
		checkIfPaymentExist(paymentId);

		Payment result = this.paymentDao.getById(paymentId);
		PaymentDto response = this.modelMapperService.forDto().map(result, PaymentDto.class);

		return new SuccessDataResult<PaymentDto>(response, Messages.PAYMENT_FOUND);
	}

	private void toSendPosService(CreatePaymentRequest createPaymentRequest) {
		posService.payment(createPaymentRequest);
	}

	private void checkIfInvoiceExists(int invoiceId) throws BusinessException {

		if (Objects.isNull(invoiceService.getById(invoiceId))) {
			throw new BusinessException(Messages.INVOICE_NOT_FOUND);
		}
	}

	private void checkIfPaymentExist(int paymentId) throws BusinessException {
		if (Objects.isNull(paymentDao.getById(paymentId))) {
			throw new BusinessException(Messages.PAYMENT_NOT_FOUND);
		}
	}

}
