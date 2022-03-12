package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.brand.BrandDto;
import com.oguzhanturk.rentacar.business.dtos.brand.ListBrandDto;
import com.oguzhanturk.rentacar.business.request.brand.CreateBrandRequest;
import com.oguzhanturk.rentacar.business.request.brand.DeleteBrandRequest;
import com.oguzhanturk.rentacar.business.request.brand.UpdateBrandRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface BrandService {

	DataResult<List<ListBrandDto>> getAll();

	DataResult<BrandDto> getById(int id) throws BusinessException;

	Result add(CreateBrandRequest createBrandRequest) throws BusinessException;

	Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException;

	Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException;

}
