package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.ListRentalDto;
import com.oguzhanturk.rentacar.business.dtos.RentalDto;
import com.oguzhanturk.rentacar.business.request.CreateRentalRequest;
import com.oguzhanturk.rentacar.business.request.DeleteRentalRequest;
import com.oguzhanturk.rentacar.business.request.UpdateRentalRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface RentalService {

	DataResult<List<ListRentalDto>> getAll();

	DataResult<RentalDto> getById(int id) throws BusinessException;

	Result add(CreateRentalRequest createRentalRequest) throws BusinessException;

	Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException;

	Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException;

	DataResult<List<ListRentalDto>> getAllByCar(int id) throws BusinessException;

	boolean isCarAlreadyRented(int carId);

}
