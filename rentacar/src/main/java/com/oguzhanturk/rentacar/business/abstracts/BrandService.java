package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.BrandDto;
import com.oguzhanturk.rentacar.business.dtos.ListBrandDto;
import com.oguzhanturk.rentacar.business.request.CreateBrandRequest;
import com.oguzhanturk.rentacar.business.request.DeleteBrandRequest;
import com.oguzhanturk.rentacar.business.request.UpdateBrandRequest;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface BrandService {

	DataResult<List<ListBrandDto>> getAll();

	DataResult<BrandDto> getById(int id);

	Result add(CreateBrandRequest createBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);

	Result delete(DeleteBrandRequest deleteBrandRequest);

}
