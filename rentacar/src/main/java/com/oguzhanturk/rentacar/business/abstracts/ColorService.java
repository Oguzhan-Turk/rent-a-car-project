package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.ColorDto;
import com.oguzhanturk.rentacar.business.dtos.ListColorDto;
import com.oguzhanturk.rentacar.business.request.CreateColorRequest;
import com.oguzhanturk.rentacar.business.request.DeleteColorRequest;
import com.oguzhanturk.rentacar.business.request.UpdateColorRequest;

public interface ColorService {

	List<ListColorDto> getAll();

	ColorDto getById(int id);

	ColorDto add(CreateColorRequest createColorRequest);

	void delete(DeleteColorRequest deleteColorRequest);
	
	void update(UpdateColorRequest updateColorRequest);

}
