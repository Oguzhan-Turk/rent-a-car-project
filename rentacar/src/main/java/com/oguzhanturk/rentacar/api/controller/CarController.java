package com.oguzhanturk.rentacar.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oguzhanturk.rentacar.business.abstracts.CarService;
import com.oguzhanturk.rentacar.business.dtos.CarDto;
import com.oguzhanturk.rentacar.business.dtos.ListCarDto;
import com.oguzhanturk.rentacar.business.request.CreateCarRequest;
import com.oguzhanturk.rentacar.business.request.DeleteCarRequest;
import com.oguzhanturk.rentacar.business.request.UpdateCarRequest;

@RestController
@RequestMapping("/api/cars")
public class CarController {

	private final CarService carService;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/getall")
	public List<ListCarDto> getAll() {
		return carService.getAll();
	}

	@GetMapping("/get")
	public CarDto get(@RequestParam int id) {
		return carService.getById(id);
	}

	@PostMapping("/save")
	public void add(@RequestBody CreateCarRequest createCarRequest) {
		carService.add(createCarRequest);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody DeleteCarRequest deleteCarRequest) {
		carService.delete(deleteCarRequest);
	}
	
	@DeleteMapping("/delete/{cardId}")
	public void delete(@PathVariable int carId) {
		carService.delete(carId);
	}

	@PutMapping("/update")
	public void delete(@RequestBody UpdateCarRequest updateCarRequest) {
		carService.update(updateCarRequest);
	}

}
