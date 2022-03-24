package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.payment.ListPaymentDto;
import com.oguzhanturk.rentacar.business.request.payment.CreatePaymentRequest;
import com.oguzhanturk.rentacar.business.request.payment.DeletePaymentRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface PaymentService {

	public Result add(CreatePaymentRequest createPaymentRequest);

	Result delete(DeletePaymentRequest deletePaymentRequest) throws BusinessException;

	DataResult<List<ListPaymentDto>> getAll();
}
