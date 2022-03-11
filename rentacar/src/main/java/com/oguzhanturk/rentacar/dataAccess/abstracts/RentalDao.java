package com.oguzhanturk.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oguzhanturk.rentacar.entities.concretes.Rental;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer> {

//	Rental getByCarCarId(int id);
	
	List<Rental> getAllByCarCarId(int carId);
	
	Rental findFirstByCarCarIdOrderByRentDate(int carId);
}
