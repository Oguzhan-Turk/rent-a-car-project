package com.oguzhanturk.rentacar.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oguzhanturk.rentacar.business.abstracts.CreditCardService;
import com.oguzhanturk.rentacar.business.dtos.creditCard.CreditCardDto;
import com.oguzhanturk.rentacar.business.dtos.creditCard.ListCreditCardDto;
import com.oguzhanturk.rentacar.business.request.creditCard.CreateCreditCardRequest;
import com.oguzhanturk.rentacar.business.request.creditCard.DeleteCreditCardRequest;
import com.oguzhanturk.rentacar.business.request.creditCard.UpdateCreditCardRequest;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {
	private CreditCardService creditCardService;

	@Autowired
	public CreditCardController(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	@GetMapping
	public DataResult<List<ListCreditCardDto>> getAll() {
		return this.creditCardService.getAll();
	}

	@GetMapping("/get/{id}")
	public DataResult<CreditCardDto> getById(@RequestParam int id) {
		return this.creditCardService.getById(id);
	}

	@PostMapping("/save")
	public Result add(@RequestBody @Valid CreateCreditCardRequest createCreditCardRequest) {
		return this.creditCardService.add(createCreditCardRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCreditCardRequest updateCreditCardRequest) {
		return this.creditCardService.update(updateCreditCardRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCreditCardRequest deleteCreditCardRequest) {
		return this.creditCardService.delete(deleteCreditCardRequest);
	}
}