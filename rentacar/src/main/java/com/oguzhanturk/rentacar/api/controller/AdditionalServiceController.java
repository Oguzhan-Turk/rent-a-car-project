package com.oguzhanturk.rentacar.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oguzhanturk.rentacar.business.abstracts.AdditionalServiceService;
import com.oguzhanturk.rentacar.business.dtos.AdditionalServiceDto;
import com.oguzhanturk.rentacar.business.dtos.ListAdditionalServiceDto;
import com.oguzhanturk.rentacar.business.request.CreateAdditionalServiceRequest;
import com.oguzhanturk.rentacar.business.request.DeleteAdditionalServiceRequest;
import com.oguzhanturk.rentacar.business.request.UpdateAdditionalServiceRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServiceController {

	private AdditionalServiceService additionalServiceService;

	@Autowired
	public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
		super();
		this.additionalServiceService = additionalServiceService;

	}

	@GetMapping("/getAll")
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		return this.additionalServiceService.getAll();
	}

	@GetMapping("/getAllByRentId/{rentId}")
	DataResult<List<ListAdditionalServiceDto>> getAllByRentId(@RequestParam("rentId") int rentId) {
		return additionalServiceService.getAllByRentId(rentId);
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest)
			throws BusinessException {
		return additionalServiceService.add(createAdditionalServiceRequest);
	}

	@GetMapping("/get/{id}")
	DataResult<AdditionalServiceDto> getById(@RequestParam("id") int additionalServiceId) throws BusinessException {
		return additionalServiceService.getById(additionalServiceId);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest)
			throws BusinessException {
		return additionalServiceService.delete(deleteAdditionalServiceRequest);
	}

	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest)
			throws BusinessException {
		return additionalServiceService.update(updateAdditionalServiceRequest);
	}
}
