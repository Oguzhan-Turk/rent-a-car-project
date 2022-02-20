package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.BrandDto;
import com.oguzhanturk.rentacar.business.dtos.ListBrandDto;
import com.oguzhanturk.rentacar.business.request.CreateBrandRequest;
import com.oguzhanturk.rentacar.business.request.DeleteBrandRequest;
import com.oguzhanturk.rentacar.business.request.UpdateBrandRequest;

public interface BrandService {

	void add(CreateBrandRequest createBrandRequest);

	void update(UpdateBrandRequest updateBrandRequest);

	void delete(DeleteBrandRequest deleteBrandRequest);

	List<ListBrandDto> getAll();

	BrandDto getById(int id);

}
