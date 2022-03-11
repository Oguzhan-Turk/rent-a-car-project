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
import com.oguzhanturk.rentacar.business.dtos.ListCarMaintenanceDto;
import com.oguzhanturk.rentacar.business.dtos.ListRentalDto;
import com.oguzhanturk.rentacar.business.dtos.RentalDto;
import com.oguzhanturk.rentacar.business.request.CreateRentalRequest;
import com.oguzhanturk.rentacar.business.request.DeleteRentalRequest;
import com.oguzhanturk.rentacar.business.request.UpdateRentalRequest;
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
		checkIfRentalExistById(id);

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
		checkIfRentalExistById(updateRentalRequest.getRentId()); // TODO check **
		Rental foundRental = rentalDao.findById(updateRentalRequest.getRentId()).get();
//		if (foundRental == null)
//			return new ErrorResult("The rental was not found!");
		checkIfAvailableForReturn(foundRental.getCar().getCarId());

		foundRental.setReturnDate(updateRentalRequest.getReturnDate());
//		Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		rentalDao.save(foundRental);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
		checkIfRentalExistById(deleteRentalRequest.getRentId());

		rentalDao.deleteById(deleteRentalRequest.getRentId());
		return new SuccessResult();
	}

	@Override
	public DataResult<List<ListRentalDto>> getAllByCar(int carId) throws BusinessException {
		carService.checkIfCarExistById(carId); // **
		List<Rental> result = rentalDao.getAllByCarCarId(carId);
		List<ListRentalDto> response = result.stream()
				.map(rental -> modelMapperService.forDto().map(rental, ListRentalDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	private void checkIfAvailableForRent(int carId, LocalDate rentDate) throws BusinessException {

		if (isCarAlreadyRented(carId)) {
			throw new BusinessException("The car is already rented");
		}
		if (isCarInMaintenance(carId, rentDate)) {
			throw new BusinessException("The car is under maintenance");
		}

	}

	private void checkIfAvailableForReturn(int carId) throws BusinessException {
		if (!isCarAlreadyRented(carId)) {
			throw new BusinessException("The car is already returned");
		}

	}

	private void checkIfRentalExistById(int rentalId) throws BusinessException {
		if (rentalDao.existsById(rentalId)) {
			new BusinessException("The rental was not found!");
		}
	}

	private boolean isCarInMaintenance(int carId, LocalDate rentDate) throws BusinessException {

		DataResult<List<ListCarMaintenanceDto>> maintenanceDtoResults = carMaintenanceService.getAllByCar(carId);
		List<ListCarMaintenanceDto> maintenanceDtos = maintenanceDtoResults.getData();

		for (ListCarMaintenanceDto carMaintenanceDto : maintenanceDtos) {
			if (Objects.isNull(carMaintenanceDto.getReturnDate())
					|| carMaintenanceDto.getReturnDate().isAfter(rentDate)) {
				return true;
			}

		}
		return false;
	}

	@Override
	public boolean isCarAlreadyRented(int carId) {
		Rental lastRentalById = rentalDao.findFirstByCarCarIdOrderByRentDate(carId);
		return Objects.nonNull(lastRentalById) && Objects.isNull(lastRentalById.getReturnDate());
	}

}
