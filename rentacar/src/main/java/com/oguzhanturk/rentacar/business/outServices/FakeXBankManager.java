package com.oguzhanturk.rentacar.business.outServices;

import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;

@Service
public class FakeXBankManager {

	public Result makePayment(String fullName, String cardNo, int Cvv) {
		System.out.println("Ödeme X bankası ile yapılmıştır.");
		return new SuccessResult("Ödeme X bankası ile yapılmıştır.");
	}
}