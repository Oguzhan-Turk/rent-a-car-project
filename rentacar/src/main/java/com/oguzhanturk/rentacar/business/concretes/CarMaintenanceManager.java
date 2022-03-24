package com.oguzhanturk.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CarMaintenanceService;
import com.oguzhanturk.rentacar.business.abstracts.CarService;
import com.oguzhanturk.rentacar.business.abstracts.RentalService;
import com.oguzhanturk.rentacar.business.constants.Messages;
import com.oguzhanturk.rentacar.business.dtos.maintenance.CarMaintenanceDto;
import com.oguzhanturk.rentacar.business.dtos.maintenance.ListCarMaintenanceDto;
import com.oguzhanturk.rentacar.business.request.maintenance.CreateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.maintenance.DeleteCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.maintenance.UpdateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.CarMaintenanceDao;
import com.oguzhanturk.rentacar.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private final CarMaintenanceDao carMaintenanceDao;
	private final ModelMapperService modelMapperService;
	private final CarService carService;
	private final RentalService rentalService;

	@Autowired
	@Lazy
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			CarService carService, RentalService rentalService) {
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		List<CarMaintenance> result = carMaintenanceDao.findAll();
		List<ListCarMaintenanceDto> response = result.stream()
				.map(carMaintenance -> modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response, Messages.CAR_MAINTENANCE_LIST);
	}

	@Override
	public DataResult<CarMaintenanceDto> getById(int maintenanceId) throws BusinessException {
		checkIfMaintenanceExistsById(maintenanceId);
		CarMaintenance carMaintenance = carMaintenanceDao.getById(maintenanceId);
		CarMaintenanceDto response = modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class);

		response.setCarId(carMaintenance.getCar().getCarId());

		return new SuccessDataResult<CarMaintenanceDto>(response, Messages.CAR_MAINTENANCE_FOUND);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {

//		CarMaintenance maintenance = new CarMaintenance();
		// TODO need a convertor for carDto
//		maintenance.setCar(carService.getById(createCarMaintenanceRequest.getCarId()));
//		maintenance.setMaintenanceDescription(createCarMaintenanceRequest.getMaintenanceDescription());
//		maintenance.setReturnDate(createCarMaintenanceRequest.getReturnDate());
//		carMaintenanceDao.save(maintenance);

//		carService.checkIfCarExistsById(createCarMaintenanceRequest.getCarId());

		checkIfAvailableForMaintenance(createCarMaintenanceRequest.getCarId());

		CarMaintenance carMaintenance = modelMapperService.forRequest().map(createCarMaintenanceRequest,
				CarMaintenance.class);
		carMaintenance.setMaintenanceId(0);
		carMaintenanceDao.save(carMaintenance);
		return new SuccessResult(Messages.CAR_MAINTENANCE_ADD);
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {

		checkIfMaintenanceExistsById(updateCarMaintenanceRequest.getMaintenanceId());

		CarMaintenance foundById = carMaintenanceDao.getById(updateCarMaintenanceRequest.getMaintenanceId());
		foundById.setReturnDate(updateCarMaintenanceRequest.getReturnDate());
		carMaintenanceDao.save(foundById);

		/*
		 * CarMaintenance carMaintenance =
		 * modelMapperService.forRequest().map(updateCarMaintenanceRequest,
		 * CarMaintenance.class); carMaintenanceDao.save(carMaintenance);
		 */

		return new SuccessResult(Messages.CAR_MAINTENANCE_UPDATE);
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException {

		checkIfMaintenanceExistsById(deleteCarMaintenanceRequest.getMaintenanceId());

		carMaintenanceDao.deleteById(deleteCarMaintenanceRequest.getMaintenanceId());
		return new SuccessResult(Messages.CAR_MAINTENANCE_DELETE);
	}

	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAllByCar(int carId) throws BusinessException {
		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException("The car with id : " + carId + " was not found!");
		}

		List<CarMaintenance> result = carMaintenanceDao.getAllByCarCarId(carId);
		List<ListCarMaintenanceDto> response = result.stream()
				.map(carMaintenance -> modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response, Messages.CAR_MAINTENANCE_LIST);
	}

	private void checkIfAvailableForMaintenance(int carId) throws BusinessException {
		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException(Messages.CAR_NOT_FOUND);
		}
		if (rentalService.isCarAlreadyRented(carId)) {
			throw new BusinessException(Messages.CAR_MAINTENANCE_RENTAL_ERROR);
		}
		if (isCarAlreadyMaintenanced(carId)) {
			throw new BusinessException(Messages.CAR_ALREADY_MAINTENANCED);
		}
	}

	private void checkIfMaintenanceExistsById(int maintanenceId) throws BusinessException {
		if (!carMaintenanceDao.existsById(maintanenceId)) {
			throw new BusinessException(Messages.CAR_MAINTENANCE_NOT_FOUND);
		}
	}

	// for rental
	@Override
	public boolean isCarInMaintenanceForRent(int carId, LocalDate rentDate) throws BusinessException {
		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException(Messages.CAR_NOT_FOUND);
		}
		List<CarMaintenance> allByCar = carMaintenanceDao.getAllByCarCarId(carId);
		for (CarMaintenance maintenance : allByCar) {
			if (Objects.isNull(maintenance.getReturnDate()) || maintenance.getReturnDate().isAfter(rentDate)) {
				return true;
			}
		}
		return false;
	}

	private boolean isCarAlreadyMaintenanced(int carId) throws BusinessException {

		List<CarMaintenance> carMaintenances = carMaintenanceDao.getAllByCarCarId(carId);

		for (CarMaintenance carMaintenance : carMaintenances) {
			if (carMaintenance.getReturnDate() == null) {
				return true;
			}
		}
		return false;
	}

}
