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

import com.oguzhanturk.rentacar.business.abstracts.RentalService;
import com.oguzhanturk.rentacar.business.dtos.rental.ListRentalDto;
import com.oguzhanturk.rentacar.business.dtos.rental.RentalDto;
import com.oguzhanturk.rentacar.business.request.rental.CreateRentalRequest;
import com.oguzhanturk.rentacar.business.request.rental.DeleteRentalRequest;
import com.oguzhanturk.rentacar.business.request.rental.UpdateRentalRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

	private final RentalService rentalService;

	@Autowired
	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@GetMapping("/getAll")
	public DataResult<List<ListRentalDto>> getAll() {
		return rentalService.getAll();
	}

	@GetMapping("/get/{id}")
	public DataResult<RentalDto> get(@RequestParam int id) throws BusinessException {
		return rentalService.getById(id);
	}

	@PostMapping("/save")
	public Result add(@RequestBody @Valid CreateRentalRequest createRentalRequest) throws BusinessException {
		return rentalService.add(createRentalRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteRentalRequest deleteRentalRequest) throws BusinessException {
		return rentalService.delete(deleteRentalRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) throws BusinessException {
		return rentalService.update(updateRentalRequest);
	}

	@GetMapping("/getAllByCar/{id}")
	public DataResult<List<ListRentalDto>> getAllByCar(@RequestParam int id) throws BusinessException {
		return rentalService.getAllByCar(id);
	}

}
