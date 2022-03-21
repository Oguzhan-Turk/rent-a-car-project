package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CarAccidentService;
import com.oguzhanturk.rentacar.business.abstracts.CarService;
import com.oguzhanturk.rentacar.business.dtos.carAccident.CarAccidentDto;
import com.oguzhanturk.rentacar.business.dtos.carAccident.ListCarAccidentDto;
import com.oguzhanturk.rentacar.business.request.carAccident.CreateCarAccidentRequest;
import com.oguzhanturk.rentacar.business.request.carAccident.DeleteCarAccidentRequest;
import com.oguzhanturk.rentacar.business.request.carAccident.UpdateCarAccidentRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.CarAccidentDao;
import com.oguzhanturk.rentacar.entities.concretes.CarAccident;

@Service
public class CarAccidentManager implements CarAccidentService {

	ModelMapperService modelMapperService;
	CarAccidentDao carAccidentDao;
	CarService carService;

	@Autowired
	public CarAccidentManager(ModelMapperService modelMapperService, CarAccidentDao carAccidentDao,
			CarService carService) {
		this.modelMapperService = modelMapperService;
		this.carAccidentDao = carAccidentDao;
		this.carService = carService;
	}

	@Override
	public DataResult<List<ListCarAccidentDto>> getAll() {

		List<CarAccident> result = carAccidentDao.findAll();
		List<ListCarAccidentDto> response = result.stream()
				.map(carAccident -> modelMapperService.forDto().map(carAccident, ListCarAccidentDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarAccidentDto>>(response, "CarAccident records are listed.");
	}

	@Override
	public DataResult<CarAccidentDto> getById(int id) throws BusinessException {
		checkIfCarAccidentExists(id);

		CarAccident carAccident = carAccidentDao.getById(id);
		CarAccidentDto response = modelMapperService.forDto().map(carAccident, CarAccidentDto.class);
		return new SuccessDataResult<CarAccidentDto>(response);
	}

	@Override
	public Result add(CreateCarAccidentRequest createCarAccidentRequest) throws BusinessException {

		checkIfCarExistsById(createCarAccidentRequest.getCarId());

		CarAccident carAccident = modelMapperService.forRequest().map(createCarAccidentRequest, CarAccident.class);

		carAccidentDao.save(carAccident);

		return new SuccessResult("CarAccident.Added");
	}

	@Override
	public Result update(UpdateCarAccidentRequest updateCarAccidentRequest) throws BusinessException {

		CarAccident carAccident = this.modelMapperService.forRequest().map(updateCarAccidentRequest, CarAccident.class);

		checkIfCarAccidentExists(updateCarAccidentRequest.getId());

		carAccidentDao.save(carAccident);

		return new SuccessResult("carAccident.Updated");
	}

	@Override
	public Result delete(DeleteCarAccidentRequest deleteCarAccidentRequest) throws BusinessException {

		checkIfCarAccidentExists(deleteCarAccidentRequest.getId());

		carAccidentDao.deleteById(deleteCarAccidentRequest.getId());

		return new SuccessResult("carAccident.Deleted");
	}

	@Override
	public DataResult<List<ListCarAccidentDto>> getAllByCarId(int carId) throws BusinessException {

		checkIfCarExistsById(carId);

		List<CarAccident> result = carAccidentDao.getAllByCarCarId(carId);

		List<ListCarAccidentDto> response = result.stream()
				.map(carAccident -> this.modelMapperService.forDto().map(carAccident, ListCarAccidentDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarAccidentDto>>(response);
	}

	private void checkIfCarAccidentExists(int carAccidentId) throws BusinessException {

		if (!carAccidentDao.existsById(carAccidentId)) {
			throw new BusinessException("CarAccident does not exist for id : " + carAccidentId);
		}
	}

	private void checkIfCarExistsById(int carId) throws BusinessException {
		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException("Car does not exist for id : " + carId);
		}
	}
}