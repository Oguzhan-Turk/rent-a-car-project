package com.oguzhanturk.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CarMaintenanceService;
import com.oguzhanturk.rentacar.business.abstracts.CarService;
import com.oguzhanturk.rentacar.business.abstracts.RentalService;
import com.oguzhanturk.rentacar.business.dtos.rental.ListRentalDto;
import com.oguzhanturk.rentacar.business.dtos.rental.RentalDto;
import com.oguzhanturk.rentacar.business.request.rental.CreateRentalRequest;
import com.oguzhanturk.rentacar.business.request.rental.DeleteRentalRequest;
import com.oguzhanturk.rentacar.business.request.rental.UpdateRentalRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.RentalDao;
import com.oguzhanturk.rentacar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	private final RentalDao rentalDao;
	private final ModelMapperService modelMapperService;
	private final CarMaintenanceService carMaintenanceService;
	private final CarService carService;

	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService,
			CarMaintenanceService carMaintenanceService, CarService carService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceService = carMaintenanceService;
		this.carService = carService;
	}

	@Override
	public DataResult<List<ListRentalDto>> getAll() {
		List<Rental> result = rentalDao.findAll();
		List<ListRentalDto> response = result.stream()
				.map(rental -> modelMapperService.forDto().map(rental, ListRentalDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public DataResult<RentalDto> getById(int id) throws BusinessException {
		checkIfRentalExistsById(id);
		Rental rental = rentalDao.getById(id);
		RentalDto response = modelMapperService.forDto().map(rental, RentalDto.class);
		return new SuccessDataResult<RentalDto>(response);
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) throws BusinessException {
		checkIfAvailableForRent(createRentalRequest.getCarId(), createRentalRequest.getRentDate());
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rentalDao.save(rental);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException {

		checkIfRentalExistsById(updateRentalRequest.getRentId());

		Rental foundRental = rentalDao.getById(updateRentalRequest.getRentId());
		checkIfAvailableForReturn(foundRental.getCar().getCarId());
		foundRental.setReturnDate(updateRentalRequest.getReturnDate());
//		Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		rentalDao.save(foundRental);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
		checkIfRentalExistsById(deleteRentalRequest.getRentId());
		rentalDao.deleteById(deleteRentalRequest.getRentId());
		return new SuccessResult();
	}

	@Override
	public DataResult<List<ListRentalDto>> getAllByCar(int carId) throws BusinessException {
		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException("The car with id : " + carId + " was not found!");
		}

		List<Rental> result = rentalDao.getAllByCarCarId(carId);
		List<ListRentalDto> response = result.stream()
				.map(rental -> modelMapperService.forDto().map(rental, ListRentalDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public boolean isCarAlreadyRented(int carId) {
		// TODO will be check
		Rental lastRentalById = rentalDao.findFirstByCarCarIdOrderByRentDate(carId);
		return Objects.nonNull(lastRentalById) && Objects.isNull(lastRentalById.getReturnDate());
	}

	private void checkIfAvailableForRent(int carId, LocalDate rentDate) throws BusinessException {

		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException("The car with id : " + carId + " was not found!");
		}
		if (isCarAlreadyRented(carId)) {
			throw new BusinessException("The car is already rented");
		}
		if (carMaintenanceService.isCarInMaintenance(carId, rentDate)) {
			throw new BusinessException("The car is under maintenance");
		}

	}

	private void checkIfAvailableForReturn(int carId) throws BusinessException {

		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException("The car with id : " + carId + " was not found!");
		}
		if (!isCarAlreadyRented(carId)) {
			throw new BusinessException("The car is already returned");
		}

	}

	private void checkIfRentalExistsById(int rentalId) throws BusinessException {
		if (rentalDao.existsById(rentalId)) {
			new BusinessException("The rental was not found!");
		}
	}

}
