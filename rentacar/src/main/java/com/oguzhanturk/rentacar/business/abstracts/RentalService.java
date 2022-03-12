package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.rental.ListRentalDto;
import com.oguzhanturk.rentacar.business.dtos.rental.RentalDto;
import com.oguzhanturk.rentacar.business.request.rental.CreateRentalRequest;
import com.oguzhanturk.rentacar.business.request.rental.DeleteRentalRequest;
import com.oguzhanturk.rentacar.business.request.rental.UpdateRentalRequest;
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
