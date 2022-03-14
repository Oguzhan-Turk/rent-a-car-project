package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CityService;
import com.oguzhanturk.rentacar.business.dtos.city.CityDto;
import com.oguzhanturk.rentacar.business.dtos.city.ListCityDto;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.CityDao;
import com.oguzhanturk.rentacar.entities.concretes.City;

@Service
public class CityManager implements CityService {

	private CityDao cityDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<CityDto> getById(int cityId) throws BusinessException {
		City city = cityDao.getById(cityId);
		CityDto response = modelMapperService.forDto().map(city, CityDto.class);
		return new SuccessDataResult<CityDto>(response);
	}

	@Override
	public DataResult<List<ListCityDto>> listAll() throws BusinessException {

		List<City> cities = this.cityDao.findAll();
		List<ListCityDto> listCityDtos = cities.stream()
				.map(city -> this.modelMapperService.forDto().map(city, ListCityDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCityDto>>(listCityDtos);
	}

	@Override
	public DataResult<List<ListCityDto>> getAllSorted(Direction direction) throws BusinessException {

		Sort sort = Sort.by(direction, "cityName");
		List<City> cities = this.cityDao.findAll(sort);
		List<ListCityDto> response = cities.stream()
				.map(city -> this.modelMapperService.forDto().map(city, ListCityDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCityDto>>(response);
	}

	@Override
	public DataResult<List<ListCityDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<City> cities = this.cityDao.findAll(pageable).getContent();
		List<ListCityDto> response = cities.stream()
				.map(city -> this.modelMapperService.forDto().map(city, ListCityDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCityDto>>(response);
	}

}