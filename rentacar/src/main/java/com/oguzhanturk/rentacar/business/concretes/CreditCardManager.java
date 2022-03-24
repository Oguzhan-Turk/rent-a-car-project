package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CreditCardService;
import com.oguzhanturk.rentacar.business.constants.Messages;
import com.oguzhanturk.rentacar.business.dtos.creditCard.CreditCardDto;
import com.oguzhanturk.rentacar.business.dtos.creditCard.ListCreditCardDto;
import com.oguzhanturk.rentacar.business.request.creditCard.CreateCreditCardRequest;
import com.oguzhanturk.rentacar.business.request.creditCard.DeleteCreditCardRequest;
import com.oguzhanturk.rentacar.business.request.creditCard.UpdateCreditCardRequest;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.CreditCardDao;
import com.oguzhanturk.rentacar.entities.concretes.CreditCard;

@Service
public class CreditCardManager implements CreditCardService {
	private CreditCardDao creditCardDao;
	private ModelMapperService modelMapperService;

	public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService) {
		this.creditCardDao = creditCardDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCreditCardDto>> getAll() {
		var result = this.creditCardDao.findAll();
		List<ListCreditCardDto> response = result.stream()
				.map(creditCard -> this.modelMapperService.forDto().map(creditCard, ListCreditCardDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCreditCardDto>>(response, Messages.CREDIT_CARD_LIST);
	}

	@Override
	public Result add(CreateCreditCardRequest createCreditCardRequest) {
		CreditCard creditCard = this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
		this.creditCardDao.save(creditCard);
		return new SuccessResult(Messages.CREDIT_CARD_ADD);
	}

	@Override
	public DataResult<CreditCardDto> getById(int id) {
		var result = this.creditCardDao.getById(id);
		CreditCardDto response = this.modelMapperService.forDto().map(result, CreditCardDto.class);

		return new SuccessDataResult<CreditCardDto>(response, Messages.CREDIT_CARD_FOUND);
	}

	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
		CreditCard creditCard = this.modelMapperService.forRequest().map(updateCreditCardRequest, CreditCard.class);
		CreditCard updated = this.creditCardDao.getById(updateCreditCardRequest.getCreditCardId());

		updated.setCardNumber(creditCard.getCardNumber());
		updated.setCvvNumber(creditCard.getCvvNumber());
		updated.setOwnerName(creditCard.getOwnerName());
		updated.setPayments(creditCard.getPayments());

		this.creditCardDao.save(updated);
		return new SuccessResult(Messages.CREDIT_CARD_UPDATE);
	}

	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		this.creditCardDao.deleteById(deleteCreditCardRequest.getCreditCardId());
		return new SuccessResult(Messages.CREDIT_CAR_DELETE);
	}

}