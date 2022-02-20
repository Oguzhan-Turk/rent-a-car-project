package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.BrandService;
import com.oguzhanturk.rentacar.business.dtos.BrandDto;
import com.oguzhanturk.rentacar.business.dtos.ListBrandDto;
import com.oguzhanturk.rentacar.business.request.CreateBrandRequest;
import com.oguzhanturk.rentacar.business.request.DeleteBrandRequest;
import com.oguzhanturk.rentacar.business.request.UpdateBrandRequest;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.dataAccess.abstracts.BrandDao;
import com.oguzhanturk.rentacar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {

	private final BrandDao brandDao;
	private final ModelMapperService modelMapperService;

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
		if (!brandDao.existsByBrandName(createBrandRequest.getBrandName())) {
			Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
			brandDao.save(brand);
		}

	}

	@Override
	public BrandDto getById(int id) {
		Brand brand = brandDao.getById(id);
		BrandDto response = modelMapperService.forDto().map(brand, BrandDto.class);
		return response;
	}

	@Override
	public void delete(DeleteBrandRequest deleteBrandRequest) {
		if (brandDao.existsById(deleteBrandRequest.getBrandId())) {
			brandDao.deleteById(deleteBrandRequest.getBrandId());
		}

	}

	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		if (brandDao.existsById(updateBrandRequest.getBrandId())) {
			Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
			brandDao.save(brand);
		}

	}

}
