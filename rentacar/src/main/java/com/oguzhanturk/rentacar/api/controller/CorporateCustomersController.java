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

import com.oguzhanturk.rentacar.business.abstracts.CorporateCustomerService;
import com.oguzhanturk.rentacar.business.dtos.corporateCustomer.CorporateCustomerDto;
import com.oguzhanturk.rentacar.business.dtos.corporateCustomer.ListCorporateCustomerDto;
import com.oguzhanturk.rentacar.business.request.corporateCustomer.CreateCorporateCustomerRequest;
import com.oguzhanturk.rentacar.business.request.corporateCustomer.DeleteCorporateCustomerRequest;
import com.oguzhanturk.rentacar.business.request.corporateCustomer.UpdateCorporateCustomerRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporateCustomers")
public class CorporateCustomersController {

	private CorporateCustomerService corporateCustomerService;

	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {

		this.corporateCustomerService = corporateCustomerService;
	}

	@GetMapping("/get/{id}")
	DataResult<CorporateCustomerDto> getById(@RequestParam int id) throws BusinessException {

		return corporateCustomerService.getById(id);
	}

	@PostMapping("/create")
	public Result create(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest)
			throws BusinessException {

		return this.corporateCustomerService.create(createCorporateCustomerRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest)
			throws BusinessException {

		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest)
			throws BusinessException {

		return corporateCustomerService.delete(deleteCorporateCustomerRequest);
	}

	@GetMapping("/listAll")
	public DataResult<List<ListCorporateCustomerDto>> listAll() throws BusinessException {

		return corporateCustomerService.listAll();
	}

	@GetMapping("/getAllSorted")
	public DataResult<List<ListCorporateCustomerDto>> getAllSorted(Sort.Direction direction) throws BusinessException {
		return this.corporateCustomerService.getAllSorted(direction);
	}

	@GetMapping("/getAllPaged")
	public DataResult<List<ListCorporateCustomerDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException {

		return this.corporateCustomerService.getAllPaged(pageNo, pageSize);
	}

}
