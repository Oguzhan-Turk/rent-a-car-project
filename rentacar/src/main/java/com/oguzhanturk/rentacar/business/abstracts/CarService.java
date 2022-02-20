package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.CarDto;
import com.oguzhanturk.rentacar.business.dtos.ListCarDto;
import com.oguzhanturk.rentacar.business.request.CreateCarRequest;
import com.oguzhanturk.rentacar.business.request.DeleteCarRequest;
import com.oguzhanturk.rentacar.business.request.UpdateCarRequest;

public interface CarService {

	List<ListCarDto> getAll();

	CarDto getById(int id);

	void add(CreateCarRequest createCarRequest);

	void delete(DeleteCarRequest deleteCarRequest);

	void update(UpdateCarRequest updateCarRequest);
}
