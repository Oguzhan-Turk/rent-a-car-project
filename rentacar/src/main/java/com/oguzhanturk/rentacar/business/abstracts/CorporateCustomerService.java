package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.oguzhanturk.rentacar.business.dtos.corporateCustomer.CorporateCustomerDto;
import com.oguzhanturk.rentacar.business.dtos.corporateCustomer.ListCorporateCustomerDto;
import com.oguzhanturk.rentacar.business.request.corporateCustomer.CreateCorporateCustomerRequest;
import com.oguzhanturk.rentacar.business.request.corporateCustomer.DeleteCorporateCustomerRequest;
import com.oguzhanturk.rentacar.business.request.corporateCustomer.UpdateCorporateCustomerRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface CorporateCustomerService {

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException;

	Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException;

	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException;

	DataResult<CorporateCustomerDto> getById(int id) throws BusinessException;

	DataResult<List<ListCorporateCustomerDto>> getAllSorted(Sort.Direction direction) throws BusinessException;

	DataResult<List<ListCorporateCustomerDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException;

	DataResult<List<ListCorporateCustomerDto>> listAll() throws BusinessException;
}
