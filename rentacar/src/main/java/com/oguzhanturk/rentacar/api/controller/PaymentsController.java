package com.oguzhanturk.rentacar.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oguzhanturk.rentacar.business.abstracts.PaymentService;
import com.oguzhanturk.rentacar.business.dtos.payment.ListPaymentDto;
import com.oguzhanturk.rentacar.business.request.payment.CreatePaymentRequest;
import com.oguzhanturk.rentacar.business.request.payment.DeletePaymentRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

	PaymentService paymentService;

	@Autowired
	public PaymentsController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) {
		return paymentService.add(createPaymentRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeletePaymentRequest deletePaymentRequest) throws BusinessException {
		return paymentService.delete(deletePaymentRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<ListPaymentDto>> getAll() {
		return paymentService.getAll();
	}

}
