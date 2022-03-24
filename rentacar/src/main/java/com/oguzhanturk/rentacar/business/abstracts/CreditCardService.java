package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.creditCard.CreditCardDto;
import com.oguzhanturk.rentacar.business.dtos.creditCard.ListCreditCardDto;
import com.oguzhanturk.rentacar.business.request.creditCard.CreateCreditCardRequest;
import com.oguzhanturk.rentacar.business.request.creditCard.DeleteCreditCardRequest;
import com.oguzhanturk.rentacar.business.request.creditCard.UpdateCreditCardRequest;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface CreditCardService {

	DataResult<List<ListCreditCardDto>> getAll();

	DataResult<CreditCardDto> getById(int id);

	Result add(CreateCreditCardRequest createCreditCardRequest);

	Result update(UpdateCreditCardRequest updateCreditCardRequest);

	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);

}
