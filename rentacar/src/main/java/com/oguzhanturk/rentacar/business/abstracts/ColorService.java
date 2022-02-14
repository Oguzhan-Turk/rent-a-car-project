package com.oguzhanturk.rentacar.business.abstracts;

import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.ListColorDto;
import com.oguzhanturk.rentacar.business.request.CreateColorRequest;

public interface ColorService {

	List<ListColorDto> getAll();

	void add(CreateColorRequest createColorRequest);

	ListColorDto getById(int id);
}
