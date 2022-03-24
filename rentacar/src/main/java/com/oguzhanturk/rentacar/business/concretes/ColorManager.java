package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.ColorService;
import com.oguzhanturk.rentacar.business.constants.Messages;
import com.oguzhanturk.rentacar.business.dtos.color.ColorDto;
import com.oguzhanturk.rentacar.business.dtos.color.ListColorDto;
import com.oguzhanturk.rentacar.business.request.color.CreateColorRequest;
import com.oguzhanturk.rentacar.business.request.color.DeleteColorRequest;
import com.oguzhanturk.rentacar.business.request.color.UpdateColorRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
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
	public DataResult<List<ListColorDto>> getAll() {
		List<Color> result = colorDao.findAll();
		List<ListColorDto> response = result.stream()
				.map(color -> modelMapperService.forDto().map(color, ListColorDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListColorDto>>(response, Messages.COLOR_LIST);
	}

	@Override
	public DataResult<ColorDto> getById(int id) throws BusinessException {
		checkIfExistsById(id);
		Color color = colorDao.getById(id);
		ColorDto response = modelMapperService.forDto().map(color, ColorDto.class);
		return new SuccessDataResult<ColorDto>(response, Messages.COLOR_FOUND);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {
		checkIfNameIsExist(createColorRequest.getColorName());
		Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
		colorDao.save(color);
		return new SuccessResult(Messages.COLOR_ADD);
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException {
		checkIfExistsById(deleteColorRequest.getColorId());
		colorDao.deleteById(deleteColorRequest.getColorId());
		return new SuccessResult(Messages.COLOR_DELETE);
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws BusinessException {
		checkIfExistsById(updateColorRequest.getColorId());
		checkIfNameIsExist(updateColorRequest.getColorName());
		Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
		colorDao.save(color);
		return new SuccessResult(Messages.COLOR_UPDATE);
	}

	private void checkIfNameIsExist(String colorName) throws BusinessException {
		if (colorDao.existsByColorName(colorName)) {
			throw new BusinessException(Messages.COLOR_NAME_ERROR);
		}
	}

	private void checkIfExistsById(int colorId) throws BusinessException {
		if (!colorDao.existsById(colorId)) {
			throw new BusinessException(Messages.COLOR_NOT_FOUND);
		}
	}

}
