package com.oguzhanturk.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oguzhanturk.rentacar.entities.concretes.Rental;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer> {

	Rental getByCarCarId(int id);
}
