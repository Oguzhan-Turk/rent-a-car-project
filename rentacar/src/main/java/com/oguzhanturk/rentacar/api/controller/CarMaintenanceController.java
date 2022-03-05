package com.oguzhanturk.rentacar.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oguzhanturk.rentacar.business.abstracts.CarMaintenanceService;
import com.oguzhanturk.rentacar.business.dtos.CarMaintenanceDto;
import com.oguzhanturk.rentacar.business.dtos.ListCarMaintenanceDto;
import com.oguzhanturk.rentacar.business.request.CreateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.DeleteCarMaintenanceRequest;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/maintenances")
public class CarMaintenanceController {

	private final CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintenanceController(CarMaintenanceService carMaintenanceService) {
		this.carMaintenanceService = carMaintenanceService;
	}

	@GetMapping("/getAll")
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		return carMaintenanceService.getAll();
	}

	@GetMapping("/get")
	public DataResult<CarMaintenanceDto> get(@RequestParam int id) {
		return carMaintenanceService.getById(id);
	}

	@PostMapping("/save")
	public Result add(@RequestBody CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		return carMaintenanceService.add(createCarMaintenanceRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		return carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}

	@GetMapping("/getAllByCar")
	public DataResult<List<ListCarMaintenanceDto>> getAllByCar(@RequestParam int id) {
		return carMaintenanceService.getAllByCar(id);
	}

}
