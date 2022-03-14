package com.oguzhanturk.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oguzhanturk.rentacar.entities.concretes.City;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {

	City getByCityName(int cityName);
	boolean existsByCityName(String cityName);

}
