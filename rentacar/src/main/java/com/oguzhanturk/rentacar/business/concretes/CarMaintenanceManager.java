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
import com.oguzhanturk.rentacar.business.dtos.CarMaintenanceDto;
import com.oguzhanturk.rentacar.business.dtos.ListCarMaintenanceDto;
import com.oguzhanturk.rentacar.business.request.CreateCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.DeleteCarMaintenanceRequest;
import com.oguzhanturk.rentacar.business.request.UpdateCarMaintenanceRequest;
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

		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
	}

	@Override
	public DataResult<CarMaintenanceDto> getById(int id) throws BusinessException {
		checkIfMaintenanceExistsById(id);
		CarMaintenance carMaintenance = carMaintenanceDao.getById(id);
		CarMaintenanceDto response = modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class);

		return new SuccessDataResult<CarMaintenanceDto>(response);
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

		if (rentalService.isCarAlreadyRented(createCarMaintenanceRequest.getCarId())) {
			throw new BusinessException("The car is rented!");
		}
		CarMaintenance carMaintenance = modelMapperService.forRequest().map(createCarMaintenanceRequest,
				CarMaintenance.class);
		carMaintenance.setMaintenanceId(0);
		carMaintenanceDao.save(carMaintenance);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {

		checkIfMaintenanceExistsById(updateCarMaintenanceRequest.getMaintenanceId());

		CarMaintenance carMaintenance = modelMapperService.forRequest().map(updateCarMaintenanceRequest,
				CarMaintenance.class);
		carMaintenanceDao.save(carMaintenance);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException {

		checkIfMaintenanceExistsById(deleteCarMaintenanceRequest.getMaintenanceId());

		carMaintenanceDao.deleteById(deleteCarMaintenanceRequest.getMaintenanceId());
		return new SuccessResult();
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

		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
	}

	@Override
	public boolean isCarInMaintenance(int carId, LocalDate rentDate) throws BusinessException {

		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException("The car with id : " + carId + " was not found!");
		}

		DataResult<List<ListCarMaintenanceDto>> maintenanceDtoResults = getAllByCar(carId);
		List<ListCarMaintenanceDto> maintenanceDtos = maintenanceDtoResults.getData();

		for (ListCarMaintenanceDto carMaintenanceDto : maintenanceDtos) {
			if (Objects.isNull(carMaintenanceDto.getReturnDate())
					|| carMaintenanceDto.getReturnDate().isAfter(rentDate)) {
				return true;
			}
		}
		return false;
	}

	private void checkIfMaintenanceExistsById(int maintanenceId) throws BusinessException {
		if (!carMaintenanceDao.existsById(maintanenceId)) {
			throw new BusinessException("The car maintanence with id : " + maintanenceId + " was not found!");
		}
	}

}
