package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.CarMaintenanceDto;
import com.oguzhanturk.rentacar.business.dtos.ListCarMaintenanceDto;
import com.oguzhanturk.rentacar.business.request.CreateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.DeleteCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.UpdateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface CarMaintenanceService {

	DataResult<List<ListCarMaintenanceDto>> getAll();

	DataResult<CarMaintenanceDto> getById(int id);

	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);

	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);

	Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);
	
	DataResult<List<ListCarMaintenanceDto>> getAllByCar(int id);

}
