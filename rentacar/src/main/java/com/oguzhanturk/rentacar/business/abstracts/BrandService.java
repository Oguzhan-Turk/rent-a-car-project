package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.ListBrandDto;
import com.oguzhanturk.rentacar.business.request.CreateBrandRequest;

public interface BrandService {

	List<ListBrandDto> getAll();

	void add(CreateBrandRequest createBrandRequest);

	ListBrandDto getById(int id);

}
