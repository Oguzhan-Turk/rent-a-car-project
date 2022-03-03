package com.oguzhanturk.rentacar.dataAccess.abstracts;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oguzhanturk.rentacar.entities.concretes.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {

	List<Car> getByDailyPriceLessThanEqual(BigDecimal dailyPrice);

}