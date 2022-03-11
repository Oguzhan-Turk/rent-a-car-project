package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.AdditionalServiceDto;
import com.oguzhanturk.rentacar.business.dtos.ListAdditionalServiceDto;
import com.oguzhanturk.rentacar.business.request.CreateAdditionalServiceRequest;
import com.oguzhanturk.rentacar.business.request.DeleteAdditionalServiceRequest;
import com.oguzhanturk.rentacar.business.request.UpdateAdditionalServiceRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface AdditionalServiceService {

	DataResult<List<ListAdditionalServiceDto>> getAll();

	DataResult<List<ListAdditionalServiceDto>> getAllByRentId(int rentId);

	DataResult<AdditionalServiceDto> getById(int additionalServiceId) throws BusinessException;

	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException;

	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException;

	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException;
}
