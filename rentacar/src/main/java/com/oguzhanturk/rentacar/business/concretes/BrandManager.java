package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.BrandService;
import com.oguzhanturk.rentacar.business.dtos.brand.BrandDto;
import com.oguzhanturk.rentacar.business.dtos.brand.ListBrandDto;
import com.oguzhanturk.rentacar.business.request.brand.CreateBrandRequest;
import com.oguzhanturk.rentacar.business.request.brand.DeleteBrandRequest;
import com.oguzhanturk.rentacar.business.request.brand.UpdateBrandRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
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
	public DataResult<List<ListBrandDto>> getAll() {
		List<Brand> result = brandDao.findAll();
		List<ListBrandDto> response = result.stream()
				.map(brand -> modelMapperService.forDto().map(brand, ListBrandDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListBrandDto>>(response);
	}

	@Override
	public DataResult<BrandDto> getById(int id) throws BusinessException {
		isExistById(id);
		Brand brand = brandDao.getById(id);
		BrandDto response = modelMapperService.forDto().map(brand, BrandDto.class);
		return new SuccessDataResult<BrandDto>(response);
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) throws BusinessException {
		checkIfNameIsExist(createBrandRequest.getBrandName());
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		brandDao.save(brand);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {
		isExistById(deleteBrandRequest.getBrandId());
		brandDao.deleteById(deleteBrandRequest.getBrandId());
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		isExistById(updateBrandRequest.getBrandId());
		checkIfNameIsExist(updateBrandRequest.getBrandName());
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		brandDao.save(brand);
		return new SuccessResult();

	}

	private void checkIfNameIsExist(String brandName) throws BusinessException {
		if (brandDao.existsByBrandName(brandName)) {
			throw new BusinessException(brandName + " already exist!");
		}
	}

	private boolean isExistById(int brandId) throws BusinessException {
		if (brandDao.existsById(brandId)) {
			return true;
		}
		throw new BusinessException("The brand with id : " + brandId + " was not found!");
	}

}
