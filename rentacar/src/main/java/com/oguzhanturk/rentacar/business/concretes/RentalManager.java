package com.oguzhanturk.rentacar.business.concretes;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CarMaintenanceService;
import com.oguzhanturk.rentacar.business.abstracts.CarService;
import com.oguzhanturk.rentacar.business.abstracts.CustomerService;
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
import com.oguzhanturk.rentacar.entities.concretes.AdditionalService;
import com.oguzhanturk.rentacar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	private final RentalDao rentalDao;
	private final ModelMapperService modelMapperService;
	private final CarMaintenanceService carMaintenanceService;
	private final CarService carService;
	private final CustomerService customerService;

	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService,
			CarMaintenanceService carMaintenanceService, CarService carService, CustomerService customerService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceService = carMaintenanceService;
		this.carService = carService;
		this.customerService = customerService;
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
		checkIfAvailableForRent(createRentalRequest.getCarId(), createRentalRequest.getUserId(),
				createRentalRequest.getRentDate());

		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);

		rental.setStartKilometer(carService.getById(createRentalRequest.getCarId()).getData().getKilometer());

//		rental.setRentalDailyPrice(calculateRentalDailyPrice(rental));
		rentalDao.save(rental);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException {

		checkIfRentalExistsById(updateRentalRequest.getRentId());

		Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		checkIfAvailableForReturn(rental.getCar().getCarId());

//		rental.setRentalDailyPrice(calculateRentalDailyPrice(rental));
//
//		rental.setRentalTotalPrice(calculateRentalTotalPrice(updateRentalRequest.getRentId()));

		rentalDao.save(rental);
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

	private void checkIfAvailableForRent(int carId, int customerId, LocalDate rentDate) throws BusinessException {

		if (!customerService.isExistById(customerId)) {
			throw new BusinessException("The customer with id : " + customerId + " was not found!");
		}
		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException("The car with id : " + carId + " was not found!");
		}
		if (isCarAlreadyRented(carId)) {
			throw new BusinessException("The car is already rented");
		}
		if (carMaintenanceService.isCarInMaintenanceForRent(carId, rentDate)) {
			throw new BusinessException("The car is under maintenance");
		}

	}

	private void checkIfAvailableForReturn(int carId) throws BusinessException {

		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException("The car with id : " + carId + " was not found!");
		}
//		if (!isCarAlreadyRented(carId)) {
//			throw new BusinessException("The car is already returned");
//		}

	}

	private void checkIfRentalExistsById(int rentalId) throws BusinessException {
		if (rentalDao.existsById(rentalId)) {
			new BusinessException("The rental was not found!");
		}
	}

	private BigDecimal calculateRentalDailyPrice(Rental rental) {
//		Rental rental = this.rentalDao.getById(rentId);
		BigDecimal totalPrice = rental.getCar().getDailyPrice();

//		for (int i = 0; i < rental.getOrderedAdditionalServices().size(); i++) {
//			totalPrice.add(rental.getOrderedAdditionalServices().get(i).getDailyPrice());
//		}

		for (AdditionalService rentedService : rental.getOrderedAdditionalServices()) {
			totalPrice.add(rentedService.getDailyPrice());
		}
		return totalPrice;
	}

	private long calculateTotalRentDate(int rentalId) throws BusinessException {
		Rental rental = rentalDao.getById(rentalId);
		long totalRentDay = Duration.between(rental.getRentDate(), rental.getReturnDate()).toDays();
		return totalRentDay;
	}

	private BigDecimal calculateRentalTotalPrice(int rentalId) throws BusinessException {
		Rental rental = rentalDao.getById(rentalId);
		BigDecimal totalPrice = rental.getRentalDailyPrice()
				.multiply(BigDecimal.valueOf(calculateTotalRentDate(rentalId)));

		if (!isCarReturnSameCity(rentalId)) {
			totalPrice.add(BigDecimal.valueOf(750));
		}
		return totalPrice;
	}

	private boolean isCarReturnSameCity(int rentalId) {
		Rental rental = rentalDao.getById(rentalId);
		if (rental.getRentCity() != rental.getReturnCity()) {
			return false;
		}
		return true;
	}

}
