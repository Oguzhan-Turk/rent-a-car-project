package com.oguzhanturk.rentacar.business.abstracts;

import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;

public interface CustomerService {

	public boolean isExistById(int customerId) throws BusinessException;
}
