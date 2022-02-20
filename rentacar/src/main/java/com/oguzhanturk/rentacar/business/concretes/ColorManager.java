package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.ColorService;
import com.oguzhanturk.rentacar.business.dtos.ColorDto;
import com.oguzhanturk.rentacar.business.dtos.ListColorDto;
import com.oguzhanturk.rentacar.business.request.CreateColorRequest;
import com.oguzhanturk.rentacar.business.request.DeleteColorRequest;
import com.oguzhanturk.rentacar.business.request.UpdateColorRequest;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.dataAccess.abstracts.ColorDao;
import com.oguzhanturk.rentacar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private final ColorDao colorDao;
	private final ModelMapperService modelMapperService;

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
	public ColorDto add(CreateColorRequest createColorRequest) {
		Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
		Color result = null;
		if (!existsByColorName(color)) {
			result = colorDao.save(color);
		}
		return modelMapperService.forDto().map(result, ColorDto.class);

	}

	@Override
	public ColorDto getById(int id) {
		Color color = colorDao.getById(id);
		ColorDto response = modelMapperService.forDto().map(color, ColorDto.class);
		return response;
	}

	@Override
	public void delete(DeleteColorRequest deleteColorRequest) {
		if (colorDao.existsById(deleteColorRequest.getColorId())) {
			colorDao.deleteById(deleteColorRequest.getColorId());
		}

	}

	@Override
	public void update(UpdateColorRequest updateColorRequest) {
		if (colorDao.existsById(updateColorRequest.getColorId())) {
			Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
			colorDao.save(color);
		}

	}

	private boolean existsByColorName(Color color) {
		return Objects.nonNull(colorDao.getByColorName(color.getColorName()));
	}

}
