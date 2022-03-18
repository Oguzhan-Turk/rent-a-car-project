package com.oguzhanturk.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.maintenance.CarMaintenanceDto;
import com.oguzhanturk.rentacar.business.dtos.maintenance.ListCarMaintenanceDto;
import com.oguzhanturk.rentacar.business.request.maintenance.CreateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.maintenance.DeleteCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.maintenance.UpdateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface CarMaintenanceService {

	DataResult<List<ListCarMaintenanceDto>> getAll();

	DataResult<CarMaintenanceDto> getById(int id) throws BusinessException;

	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException;

	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException;

	Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException;
	
	DataResult<List<ListCarMaintenanceDto>> getAllByCar(int id) throws BusinessException;

	boolean isCarInMaintenanceForRent(int carId, LocalDate rentDate) throws BusinessException;

}
