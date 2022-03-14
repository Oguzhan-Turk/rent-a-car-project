package com.oguzhanturk.rentacar.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oguzhanturk.rentacar.business.abstracts.CityService;
import com.oguzhanturk.rentacar.business.dtos.city.CityDto;
import com.oguzhanturk.rentacar.business.dtos.city.ListCityDto;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;

@RestController
@RequestMapping("/api/cities")
public class CityController {

	private CityService cityService;

	@Autowired
	public CityController(CityService cityService) {

		this.cityService = cityService;
	}

	@GetMapping("/get/{id}")
	DataResult<CityDto> getById(@RequestParam int id) throws BusinessException {

		return cityService.getById(id);
	}

	@GetMapping("/listAll")
	public DataResult<List<ListCityDto>> listAll() throws BusinessException {

		return cityService.listAll();
	}

	@GetMapping("/getAllSorted")
	public DataResult<List<ListCityDto>> getAllSorted(Sort.Direction direction) throws BusinessException {

		return cityService.getAllSorted(direction);
	}

	@GetMapping("/getAllPaged")
	public DataResult<List<ListCityDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException {

		return cityService.getAllPaged(pageNo, pageSize);
	}
}
