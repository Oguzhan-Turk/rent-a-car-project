package com.oguzhanturk.rentacar.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oguzhanturk.rentacar.business.abstracts.IndividualCustomerService;
import com.oguzhanturk.rentacar.business.dtos.individualCustomer.IndividualCustomerDto;
import com.oguzhanturk.rentacar.business.dtos.individualCustomer.ListIndividualCustomerDto;
import com.oguzhanturk.rentacar.business.request.individualCustomer.CreateIndividualCustomerRequest;
import com.oguzhanturk.rentacar.business.request.individualCustomer.DeleteIndividualCustomerRequest;
import com.oguzhanturk.rentacar.business.request.individualCustomer.UpdateIndividualCustomerRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualCustomers")
public class IndividualCustomersController {

	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {

		this.individualCustomerService = individualCustomerService;
	}

	@GetMapping("/get/{id}")
	DataResult<IndividualCustomerDto> getByCorporateCustomerId(@RequestParam int id) throws BusinessException {

		return individualCustomerService.getById(id);
	}

	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest)
			throws BusinessException {

		return individualCustomerService.create(createIndividualCustomerRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest)
			throws BusinessException {

		return individualCustomerService.update(updateIndividualCustomerRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest)
			throws BusinessException {

		return individualCustomerService.delete(deleteIndividualCustomerRequest);
	}

	@GetMapping("/listAll")
	public DataResult<List<ListIndividualCustomerDto>> listAll() throws BusinessException {

		return individualCustomerService.listAll();
	}

	@GetMapping("/getAllSorted")
	public DataResult<List<ListIndividualCustomerDto>> getAllSorted(Sort.Direction direction) throws BusinessException {

		return individualCustomerService.getAllSorted(direction);
	}

	@GetMapping("/getAllPaged")
	public DataResult<List<ListIndividualCustomerDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException {

		return individualCustomerService.getAllPaged(pageNo, pageSize);
	}

}
