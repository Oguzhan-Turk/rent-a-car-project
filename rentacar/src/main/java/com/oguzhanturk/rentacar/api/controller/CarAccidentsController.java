package com.oguzhanturk.rentacar.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oguzhanturk.rentacar.business.abstracts.CarAccidentService;
import com.oguzhanturk.rentacar.business.dtos.carAccident.CarAccidentDto;
import com.oguzhanturk.rentacar.business.dtos.carAccident.ListCarAccidentDto;
import com.oguzhanturk.rentacar.business.request.carAccident.CreateCarAccidentRequest;
import com.oguzhanturk.rentacar.business.request.carAccident.DeleteCarAccidentRequest;
import com.oguzhanturk.rentacar.business.request.carAccident.UpdateCarAccidentRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/carAccidents")
public class CarAccidentsController {
	private final CarAccidentService carAccidentService;

	public CarAccidentsController(CarAccidentService carAccidentService) {
		this.carAccidentService = carAccidentService;
	}

	@GetMapping("/getall")
	public DataResult<List<ListCarAccidentDto>> getAll() {
		return carAccidentService.getAll();
	}

	@GetMapping("/get/{id}")
	public DataResult<CarAccidentDto> getById(@RequestParam int id) throws BusinessException {
		return carAccidentService.getById(id);
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarAccidentRequest createCarAccidentRequest) throws BusinessException {
		return carAccidentService.add(createCarAccidentRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarAccidentRequest updateCarAccidentRequest)
			throws BusinessException {
		return carAccidentService.update(updateCarAccidentRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarAccidentRequest deleteCarAccidentRequest)
			throws BusinessException {
		return carAccidentService.delete(deleteCarAccidentRequest);
	}

	@GetMapping("/getAllByCar/{carId}")
	public DataResult<List<ListCarAccidentDto>> getAllByCarId(@RequestParam int carId) throws BusinessException {
		return carAccidentService.getAllByCarId(carId);
	}

}
