package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.oguzhanturk.rentacar.business.dtos.CarDto;
import com.oguzhanturk.rentacar.business.dtos.ListCarDto;
import com.oguzhanturk.rentacar.business.request.CreateCarRequest;
import com.oguzhanturk.rentacar.business.request.DeleteCarRequest;
import com.oguzhanturk.rentacar.business.request.UpdateCarRequest;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface CarService {

	DataResult<List<ListCarDto>> getAll();

	DataResult<CarDto> getById(int id);

	Result add(CreateCarRequest createCarRequest);

	Result delete(DeleteCarRequest deleteCarRequest);

//	Result delete(int carId);

	Result update(UpdateCarRequest updateCarRequest);

	DataResult<List<ListCarDto>> getByDailyPriceLessThan(double maxDailyPrice);

	DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);

	DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction);

}
