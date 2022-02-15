package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.BrandService;
import com.oguzhanturk.rentacar.business.dtos.ListBrandDto;
import com.oguzhanturk.rentacar.business.request.CreateBrandRequest;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.dataAccess.abstracts.BrandDao;
import com.oguzhanturk.rentacar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ListBrandDto> getAll() {
		List<Brand> result = brandDao.findAll();
		List<ListBrandDto> response = result.stream()
				.map(brand -> modelMapperService.forDto().map(brand, ListBrandDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		if (!doesExist(brand)) {
			brandDao.save(brand);
		}

	}

	@Override
	public ListBrandDto getById(int id) {
		Brand brand = brandDao.getById(id);
		ListBrandDto response = modelMapperService.forDto().map(brand, ListBrandDto.class);
		return response;
	}

	private boolean doesExist(Brand brand) {

		return Objects.nonNull(brandDao.getByBrandName(brand.getBrandName()));
	}

}
