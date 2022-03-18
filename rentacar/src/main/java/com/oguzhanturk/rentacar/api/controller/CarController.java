package com.oguzhanturk.rentacar.api.controller;

import java.math.BigDecimal;
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

import com.oguzhanturk.rentacar.business.abstracts.CarService;
import com.oguzhanturk.rentacar.business.dtos.car.CarDto;
import com.oguzhanturk.rentacar.business.dtos.car.ListCarDto;
import com.oguzhanturk.rentacar.business.request.car.CreateCarRequest;
import com.oguzhanturk.rentacar.business.request.car.DeleteCarRequest;
import com.oguzhanturk.rentacar.business.request.car.UpdateCarRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarController {

	private final CarService carService;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/getAll")
	public DataResult<List<ListCarDto>> getAll() {
		return carService.getAll();
	}

	@GetMapping("/get/{id}")
	public DataResult<CarDto> get(@RequestParam int id) throws BusinessException {
		return carService.getById(id);
	}

	@PostMapping("/save")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		return carService.add(createCarRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) throws BusinessException {
		return carService.delete(deleteCarRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) throws BusinessException {
		return carService.update(updateCarRequest);
	}

	@GetMapping("/getByDailyPriceLessThanEqual")
	public DataResult<List<ListCarDto>> getByDailyPriceLessThanEqual(BigDecimal maxDailyPrice) {
		return carService.getByDailyPriceLessThan(maxDailyPrice);
	}

	@GetMapping("/getAllSorted")
	public DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction) {
		return carService.getAllSorted(direction);
	}

	@GetMapping("/getAllPaged")
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		return carService.getAllPaged(pageNo, pageSize);
	}

}
