package com.oguzhanturk.rentacar.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oguzhanturk.rentacar.business.abstracts.ColorService;
import com.oguzhanturk.rentacar.business.dtos.color.ColorDto;
import com.oguzhanturk.rentacar.business.dtos.color.ListColorDto;
import com.oguzhanturk.rentacar.business.request.color.CreateColorRequest;
import com.oguzhanturk.rentacar.business.request.color.DeleteColorRequest;
import com.oguzhanturk.rentacar.business.request.color.UpdateColorRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

	private final ColorService colorService;

	@Autowired
	public ColorController(ColorService colorService) {
		this.colorService = colorService;
	}

	@GetMapping("/getall")
	public DataResult<List<ListColorDto>> getAll() {
		return colorService.getAll();
	}

	@GetMapping("/get/{id}")
	public DataResult<ColorDto> get(@RequestParam int id) throws BusinessException {
		return colorService.getById(id);
	}

	@PostMapping("/save")
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) throws BusinessException {
		return colorService.add(createColorRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) throws BusinessException {
		return colorService.delete(deleteColorRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) throws BusinessException {
		return colorService.update(updateColorRequest);
	}

//
//	@PostMapping("/save")
//	public ResponseEntity<ColorDto> add(@RequestBody CreateColorRequest createColorRequest) {
//		ColorDto result = null;
//		try {
//			result = colorService.add(createColorRequest);
//		} catch (IllegalArgumentException e) {
//			return new ResponseEntity<ColorDto>(HttpStatus.CONFLICT);
//		}
//		return new ResponseEntity<ColorDto>(result, HttpStatus.OK);
//	}
}
