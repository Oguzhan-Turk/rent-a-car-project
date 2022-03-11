package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.AdditionalServiceService;
import com.oguzhanturk.rentacar.business.dtos.AdditionalServiceDto;
import com.oguzhanturk.rentacar.business.dtos.ListAdditionalServiceDto;
import com.oguzhanturk.rentacar.business.request.CreateAdditionalServiceRequest;
import com.oguzhanturk.rentacar.business.request.DeleteAdditionalServiceRequest;
import com.oguzhanturk.rentacar.business.request.UpdateAdditionalServiceRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.AdditionalServiceDao;
import com.oguzhanturk.rentacar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	private ModelMapperService modelMapperService;
	private AdditionalServiceDao additionalServiceDao;

	@Autowired
	public AdditionalServiceManager(ModelMapperService modelMapperService, AdditionalServiceDao additionalServiceDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.additionalServiceDao = additionalServiceDao;
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		List<AdditionalService> result = additionalServiceDao.findAll();
		List<ListAdditionalServiceDto> response = result.stream().map(
				additionalService -> modelMapperService.forDto().map(additionalService, ListAdditionalServiceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response);
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAllByRentId(int rentId) {
		List<AdditionalService> result = additionalServiceDao.getAllByRentId(rentId);

		List<ListAdditionalServiceDto> response = result.stream()
				.map(rentalService -> modelMapperService.forDto().map(rentalService, ListAdditionalServiceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = modelMapperService.forRequest().map(createAdditionalServiceRequest,
				AdditionalService.class);
		additionalServiceDao.save(additionalService);
		return new SuccessResult("additionalService.Added");
	}

	@Override
	public DataResult<AdditionalServiceDto> getById(int additionalServiceId) throws BusinessException {
		AdditionalService result = additionalServiceDao.getById(additionalServiceId);
		if (Objects.nonNull(result)) {
			AdditionalServiceDto response = modelMapperService.forDto().map(result, AdditionalServiceDto.class);
			return new SuccessDataResult<AdditionalServiceDto>(response);
		}
		throw new BusinessException("The additional service was not found!");
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {
		isExistById(deleteAdditionalServiceRequest.getAdditionalId());
		additionalServiceDao.deleteById(deleteAdditionalServiceRequest.getAdditionalId());
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = modelMapperService.forRequest().map(updateAdditionalServiceRequest,
				AdditionalService.class);
		isExistById(updateAdditionalServiceRequest.getAdditionalId());

		this.additionalServiceDao.save(additionalService);
		return new SuccessResult("additionalService.Updated");
	}

	private boolean isExistById(int additionalServiceId) throws BusinessException {
		if (additionalServiceDao.existsById(additionalServiceId)) {
			return true;
		}
		throw new BusinessException("The brand with id : " + additionalServiceId + " was not found!");
	}

}
