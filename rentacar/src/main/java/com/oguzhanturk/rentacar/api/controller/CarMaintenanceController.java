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

import com.oguzhanturk.rentacar.business.abstracts.CarMaintenanceService;
import com.oguzhanturk.rentacar.business.dtos.maintenance.CarMaintenanceDto;
import com.oguzhanturk.rentacar.business.dtos.maintenance.ListCarMaintenanceDto;
import com.oguzhanturk.rentacar.business.request.maintenance.CreateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.maintenance.DeleteCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.maintenance.UpdateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
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

	@GetMapping("/get/{id}")
	public DataResult<CarMaintenanceDto> get(@RequestParam int id) throws BusinessException {
		return carMaintenanceService.getById(id);
	}

	@PostMapping("/save")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest)
			throws BusinessException {
		return carMaintenanceService.add(createCarMaintenanceRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deleteCarMaintenanceRequest)
			throws BusinessException {
		return carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}

	@PutMapping("/update")
	public Result delete(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest)
			throws BusinessException {
		return carMaintenanceService.update(updateCarMaintenanceRequest);
	}

	@GetMapping("/getAllByCar/{id}")
	public DataResult<List<ListCarMaintenanceDto>> getAllByCar(@RequestParam int id) throws BusinessException {
		return carMaintenanceService.getAllByCar(id);
	}

}
