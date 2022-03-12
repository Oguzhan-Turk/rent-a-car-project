package com.oguzhanturk.rentacar.business.abstracts;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.oguzhanturk.rentacar.business.dtos.car.CarDto;
import com.oguzhanturk.rentacar.business.dtos.car.ListCarDto;
import com.oguzhanturk.rentacar.business.request.car.CreateCarRequest;
import com.oguzhanturk.rentacar.business.request.car.DeleteCarRequest;
import com.oguzhanturk.rentacar.business.request.car.UpdateCarRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface CarService {

	DataResult<List<ListCarDto>> getAll();

	DataResult<CarDto> getById(int id) throws BusinessException;

	Result add(CreateCarRequest createCarRequest);

	Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException;

	Result update(UpdateCarRequest updateCarRequest) throws BusinessException;

	DataResult<List<ListCarDto>> getByDailyPriceLessThan(BigDecimal maxDailyPrice);

	DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);

	DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction);

	boolean isCarExistsById(int carId);

//	void checkIfCarExistsById(int carId) throws BusinessException;

}
