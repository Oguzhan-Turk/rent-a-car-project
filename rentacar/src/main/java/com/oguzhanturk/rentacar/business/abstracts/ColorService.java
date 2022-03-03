package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.ColorDto;
import com.oguzhanturk.rentacar.business.dtos.ListColorDto;
import com.oguzhanturk.rentacar.business.request.CreateColorRequest;
import com.oguzhanturk.rentacar.business.request.DeleteColorRequest;
import com.oguzhanturk.rentacar.business.request.UpdateColorRequest;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface ColorService {

	DataResult<List<ListColorDto>> getAll();

	DataResult<ColorDto> getById(int id);

	Result add(CreateColorRequest createColorRequest);

	Result delete(DeleteColorRequest deleteColorRequest);
	
	Result update(UpdateColorRequest updateColorRequest);

}
