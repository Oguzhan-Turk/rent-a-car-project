package com.oguzhanturk.rentacar.business.concretes;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CarService;
import com.oguzhanturk.rentacar.business.constants.Messages;
import com.oguzhanturk.rentacar.business.dtos.car.CarDto;
import com.oguzhanturk.rentacar.business.dtos.car.ListCarDto;
import com.oguzhanturk.rentacar.business.request.car.CreateCarRequest;
import com.oguzhanturk.rentacar.business.request.car.DeleteCarRequest;
import com.oguzhanturk.rentacar.business.request.car.UpdateCarRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.CarDao;
import com.oguzhanturk.rentacar.entities.concretes.Car;

@Service
public class CarManager implements CarService {

	private final CarDao carDao;
	private final ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCarDto>> getAll() {
		List<Car> result = carDao.findAll();
		List<ListCarDto> response = result.stream().map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarDto>>(response, Messages.CAR_LIST);
	}

	@Override
	public DataResult<CarDto> getById(int id) throws BusinessException {
		checkIfCarExistsById(id);
		Car car = carDao.getById(id);
		CarDto response = modelMapperService.forDto().map(car, CarDto.class);
		return new SuccessDataResult<CarDto>(response, Messages.CAR_FOUND);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		carDao.save(car);
		return new SuccessResult(Messages.CAR_ADD);
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
		checkIfCarExistsById(deleteCarRequest.getCarId());
		carDao.deleteById(deleteCarRequest.getCarId());
		return new SuccessResult(Messages.CAR_DELETE);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {
		checkIfCarExistsById(updateCarRequest.getCarId());
		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		carDao.save(car);
		return new SuccessResult(Messages.CAR_UPDATE);
	}

	@Override
	public DataResult<List<ListCarDto>> getByDailyPriceLessThan(BigDecimal maxDailyPrice) {
		var result = carDao.getByDailyPriceLessThanEqual(maxDailyPrice);

		List<ListCarDto> response = result.stream().map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDto>>(response, Messages.CAR_LIST);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Car> result = carDao.findAll(pageable).getContent();
		List<ListCarDto> response = result.stream().map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDto>>(response, Messages.CAR_LIST);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllSorted(Direction direction) {
		Sort sort = Sort.by(direction, "dailyPrice");
		List<Car> result = carDao.findAll(sort);
		List<ListCarDto> response = result.stream().map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDto>>(response, Messages.CAR_LIST);
	}

	@Override
	public boolean isCarExistsById(int carId) {
		if (carDao.existsById(carId)) {
			return true;
		}
		return false;
	}

	private void checkIfCarExistsById(int carId) throws BusinessException {
		if (!isCarExistsById(carId)) {
			throw new BusinessException(Messages.CAR_NOT_FOUND);
		}
	}

}
