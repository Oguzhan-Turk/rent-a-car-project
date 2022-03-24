package com.oguzhanturk.rentacar.business.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CreditCardService;
import com.oguzhanturk.rentacar.business.abstracts.PosService;
import com.oguzhanturk.rentacar.business.dtos.creditCard.CreditCardDto;
import com.oguzhanturk.rentacar.business.outServices.FakeXBankManager;
import com.oguzhanturk.rentacar.business.request.payment.CreatePaymentRequest;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@Service
@Primary
public class FakeXBankServiceAdapter implements PosService {
	private final CreditCardService creditCardService;

	@Autowired
	public FakeXBankServiceAdapter(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	@Override
	public Result payment(CreatePaymentRequest createPaymentRequest) {
		FakeXBankManager fakeXManager = new FakeXBankManager();
		CreditCardDto creditCardDto = creditCardService.getById(createPaymentRequest.getCreditCardId()).getData();
		return fakeXManager.makePayment(creditCardDto.getOwnerName(), creditCardDto.getCardNumber(),
				creditCardDto.getCvvNumber());

	}

}
