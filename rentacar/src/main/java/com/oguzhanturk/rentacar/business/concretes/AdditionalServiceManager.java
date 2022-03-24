package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.AdditionalServiceService;
import com.oguzhanturk.rentacar.business.abstracts.RentalService;
import com.oguzhanturk.rentacar.business.constants.Messages;
import com.oguzhanturk.rentacar.business.dtos.additionalService.AdditionalServiceDto;
import com.oguzhanturk.rentacar.business.dtos.additionalService.ListAdditionalServiceDto;
import com.oguzhanturk.rentacar.business.request.additionalService.CreateAdditionalServiceRequest;
import com.oguzhanturk.rentacar.business.request.additionalService.DeleteAdditionalServiceRequest;
import com.oguzhanturk.rentacar.business.request.additionalService.UpdateAdditionalServiceRequest;
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
	private RentalService rentalService;

	@Autowired
	public AdditionalServiceManager(ModelMapperService modelMapperService, AdditionalServiceDao additionalServiceDao,
			RentalService rentalService) {
		this.modelMapperService = modelMapperService;
		this.additionalServiceDao = additionalServiceDao;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		List<AdditionalService> result = additionalServiceDao.findAll();
		List<ListAdditionalServiceDto> response = result.stream().map(
				additionalService -> modelMapperService.forDto().map(additionalService, ListAdditionalServiceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response, Messages.ADDITIONAL_SERVICE_LIST);
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAllByRentId(int rentId) {
		List<AdditionalService> result = additionalServiceDao.getAllByRental(rentId);

		List<ListAdditionalServiceDto> response = result.stream()
				.map(rentalService -> modelMapperService.forDto().map(rentalService, ListAdditionalServiceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response, Messages.ADDITIONAL_SERVICE_LIST);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = modelMapperService.forRequest().map(createAdditionalServiceRequest,
				AdditionalService.class);

//		additionalService.setAdditionalId(0);
		additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.ADDITIONAL_SERVICE_ADD);
	}

	@Override
	public DataResult<AdditionalServiceDto> getById(int additionalServiceId) throws BusinessException {
		AdditionalService result = additionalServiceDao.getById(additionalServiceId);
		if (Objects.nonNull(result)) {
			AdditionalServiceDto response = modelMapperService.forDto().map(result, AdditionalServiceDto.class);
			return new SuccessDataResult<AdditionalServiceDto>(response, Messages.ADDITIONAL_SERVICE_GET);
		}
		throw new BusinessException(Messages.ADDITIONAL_SERVICE_NOT_FOUND);
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = modelMapperService.forRequest().map(updateAdditionalServiceRequest,
				AdditionalService.class);
//		AdditionalService additionalService = additionalServiceDao.getById(updateAdditionalServiceRequest.getAdditionalId());
		isExistById(updateAdditionalServiceRequest.getAdditionalId());
//		additionalService.setDailyPrice(updateAdditionalServiceRequest.getDailyPrice());
//		additionalService.setDescription(updateAdditionalServiceRequest.getDescription());
//		additionalService.setName(updateAdditionalServiceRequest.getName());
		additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.ADDITIONAL_SERVICE_UPDATE);
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {
		isExistById(deleteAdditionalServiceRequest.getAdditionalId());
		additionalServiceDao.deleteById(deleteAdditionalServiceRequest.getAdditionalId());
		return new SuccessResult(Messages.ACCIDENT_DELETE);
	}

	private boolean isExistById(int additionalServiceId) throws BusinessException {
		if (additionalServiceDao.existsById(additionalServiceId)) {
			return true;
		}
		throw new BusinessException(Messages.ADDITIONAL_SERVICE_NOT_FOUND);
	}

}
