package com.oguzhanturk.rentacar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CustomerService;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.dataAccess.abstracts.CustomerDao;

@Service
public class CustomerManager implements CustomerService {

	private final ModelMapperService modelMapperService;
	private final CustomerDao customerDao;

	@Autowired
	public CustomerManager(ModelMapperService modelMapperService, CustomerDao customerDao) {
		this.modelMapperService = modelMapperService;
		this.customerDao = customerDao;
	}

	@Override
	public boolean isExistById(int customerId) throws BusinessException {
		if (customerDao.existsById(customerId)) {
			return true;
		}
		throw new BusinessException("The customer with id : " + customerId + " was not found!");
	}
}
