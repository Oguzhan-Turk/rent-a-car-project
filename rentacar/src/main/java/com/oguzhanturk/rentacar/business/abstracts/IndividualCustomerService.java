package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.oguzhanturk.rentacar.business.dtos.individualCustomer.IndividualCustomerDto;
import com.oguzhanturk.rentacar.business.dtos.individualCustomer.ListIndividualCustomerDto;
import com.oguzhanturk.rentacar.business.request.individualCustomer.CreateIndividualCustomerRequest;
import com.oguzhanturk.rentacar.business.request.individualCustomer.DeleteIndividualCustomerRequest;
import com.oguzhanturk.rentacar.business.request.individualCustomer.UpdateIndividualCustomerRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface IndividualCustomerService {

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException;

	Result create(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException;

	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException;

	DataResult<IndividualCustomerDto> getById(int id) throws BusinessException;

	DataResult<List<ListIndividualCustomerDto>> getAllSorted(Sort.Direction direction) throws BusinessException;

	DataResult<List<ListIndividualCustomerDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException;

	DataResult<List<ListIndividualCustomerDto>> listAll() throws BusinessException;
}
