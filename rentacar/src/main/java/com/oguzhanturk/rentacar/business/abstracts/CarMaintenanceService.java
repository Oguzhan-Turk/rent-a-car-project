package com.oguzhanturk.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.CarMaintenanceDto;
import com.oguzhanturk.rentacar.business.dtos.ListCarMaintenanceDto;
import com.oguzhanturk.rentacar.business.request.CreateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.DeleteCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.UpdateCarMaintenanceRequest;
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

	boolean isCarInMaintenance(int carId, LocalDate rentDate) throws BusinessException;

}
