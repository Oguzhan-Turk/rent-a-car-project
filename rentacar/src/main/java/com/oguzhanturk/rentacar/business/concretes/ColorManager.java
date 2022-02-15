package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.ColorService;
import com.oguzhanturk.rentacar.business.dtos.ListColorDto;
import com.oguzhanturk.rentacar.business.request.CreateColorRequest;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.dataAccess.abstracts.ColorDao;
import com.oguzhanturk.rentacar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ListColorDto> getAll() {
		List<Color> result = colorDao.findAll();
		List<ListColorDto> response = result.stream()
				.map(color -> modelMapperService.forDto().map(color, ListColorDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public void add(CreateColorRequest createColorRequest) {
		Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
		if (!doesExist(color)) {
			colorDao.save(color);
		}
	}

	@Override
	public ListColorDto getById(int id) {
		Color color = colorDao.getById(id);
		ListColorDto response = modelMapperService.forDto().map(color, ListColorDto.class);
		return response;
	}

	private boolean doesExist(Color color) {
		return Objects.nonNull(colorDao.getByColorName(color.getColorName()));
	}

}
