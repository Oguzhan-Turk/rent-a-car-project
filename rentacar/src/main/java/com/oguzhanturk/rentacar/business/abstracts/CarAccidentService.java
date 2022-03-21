package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.carAccident.CarAccidentDto;
import com.oguzhanturk.rentacar.business.dtos.carAccident.ListCarAccidentDto;
import com.oguzhanturk.rentacar.business.request.carAccident.CreateCarAccidentRequest;
import com.oguzhanturk.rentacar.business.request.carAccident.DeleteCarAccidentRequest;
import com.oguzhanturk.rentacar.business.request.carAccident.UpdateCarAccidentRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface CarAccidentService {

	DataResult<List<ListCarAccidentDto>> getAll();

	Result add(CreateCarAccidentRequest createCarAccidentRequest) throws BusinessException;

	Result update(UpdateCarAccidentRequest updateCarAccidentRequest) throws BusinessException;

	Result delete(DeleteCarAccidentRequest deleteCarAccidentRequest) throws BusinessException;

	DataResult<CarAccidentDto> getById(int id) throws BusinessException;

	DataResult<List<ListCarAccidentDto>> getAllByCarId(int carId) throws BusinessException;

}
