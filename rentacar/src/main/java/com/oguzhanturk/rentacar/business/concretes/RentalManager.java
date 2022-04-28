package com.oguzhanturk.rentacar.business.concretes;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CarMaintenanceService;
import com.oguzhanturk.rentacar.business.abstracts.CarService;
import com.oguzhanturk.rentacar.business.abstracts.CustomerService;
import com.oguzhanturk.rentacar.business.abstracts.RentalService;
import com.oguzhanturk.rentacar.business.constants.Messages;
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
        return new SuccessDataResult<List<ListRentalDto>>(response, Messages.RENTAL_LIST);
    }

    @Override
    public DataResult<RentalDto> getById(int id) throws BusinessException {
        checkIfRentalExistsById(id);
        Rental rental = rentalDao.getById(id);
        RentalDto response = modelMapperService.forDto().map(rental, RentalDto.class);
        return new SuccessDataResult<RentalDto>(response, Messages.RENTAL_FOUND);
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) throws BusinessException {

        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rental.setRentId(0);

        checkIfAvailableForRent(rental);
        rental.setStartKilometer(carService.getById(createRentalRequest.getCarCarId()).getData().getKilometer());
        rental.setRentalDailyPrice(calculateRentalDailyPrice(rental));

//		System.out.println(rental.getCustomer());
        rentalDao.save(rental);

        return new SuccessResult(Messages.RENTAL_ADD);
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException {

        checkIfRentalExistsById(updateRentalRequest.getRentId());

        Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
//		System.out.println(rental.getCustomer());

        checkIfAvailableForReturn(rental.getCar().getCarId());

        rental.setStartKilometer(carService.getById(updateRentalRequest.getCarCarId()).getData().getKilometer());
        rental.setRentalDailyPrice(calculateRentalDailyPrice(rental));
        rental.setRentalTotalPrice(calculateRentalTotalPrice(rental));

        rentalDao.save(rental);
        return new SuccessResult(Messages.RENTAL_UPDATE);
    }

    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
        checkIfRentalExistsById(deleteRentalRequest.getRentId());
        rentalDao.deleteById(deleteRentalRequest.getRentId());
        return new SuccessResult(Messages.RENTAL_DELETE);
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
        return new SuccessDataResult<List<ListRentalDto>>(response, Messages.RENTAL_LIST);
    }

    @Override
    public boolean isCarAlreadyRented(int carId) {
        // TODO will be check
        Rental lastRentalById = rentalDao.findFirstByCarCarIdOrderByRentDate(carId);
        return Objects.nonNull(lastRentalById) && Objects.isNull(lastRentalById.getReturnDate());
    }

    private void checkIfAvailableForRent(Rental rental) throws BusinessException {

        if (!customerService.isExistById(rental.getCustomer().getCustomerId())) {
            throw new BusinessException(Messages.CUSTOMER_NOT_FOUND);
        }
        if (!carService.isCarExistsById(rental.getCar().getCarId())) {
            throw new BusinessException(Messages.CAR_NOT_FOUND);
        }
        if (isCarAlreadyRented(rental.getCar().getCarId())) {
            throw new BusinessException(Messages.CAR_ALREADY_RENTED);
        }
        if (carMaintenanceService.isCarInMaintenanceForRent(rental.getCar().getCarId(), rental.getRentDate())) {
            throw new BusinessException(Messages.CAR_ALREADY_MAINTENANCED);
        }

    }

    private void checkIfAvailableForReturn(int carId) throws BusinessException {

        if (!carService.isCarExistsById(carId)) {
            throw new BusinessException(Messages.CAR_NOT_FOUND);
        }
    }

    private double calculateRentalTotalPrice(Rental rental) throws BusinessException {

        double totalPrice = calculateRentalDailyPrice(rental) * calculateTotalRentDate(rental);

        if (!isCarReturnSameCity(rental)) {
            totalPrice += 750;
        }
        return totalPrice;
    }

    private double calculateRentalDailyPrice(Rental rental) {
        double totalPrice = 0;
        if (Objects.nonNull(rental.getCar().getDailyPrice())) {
            totalPrice = rental.getCar().getDailyPrice();
            totalPrice += calculateAdditionalServicesDailyPrice(rental.getAdditionalServices());
        }
        return totalPrice;
    }

    private long calculateTotalRentDate(Rental rental) throws BusinessException {
        long totalRentDay = Duration.between(rental.getRentDate(), rental.getReturnDate()).toDays();
        return totalRentDay;
    }

    private boolean isCarReturnSameCity(Rental rental) {
        if (rental.getFromCity() != rental.getToCity()) {
            return false;
        }
        return true;
    }

    private double calculateAdditionalServicesDailyPrice(List<AdditionalService> additionalServices) {

        double totalPrice = 0;
        if (Objects.nonNull(additionalServices)) {
            for (AdditionalService rentedService : additionalServices) {
                totalPrice += rentedService.getAdditionalServiceDailyPrice();
            }
        }
        return totalPrice;
    }

    private void checkIfRentalExistsById(int rentalId) throws BusinessException {
        if (rentalDao.existsById(rentalId)) {
            new BusinessException(Messages.RENTAL_NOT_FOUND);
        }
    }

}
