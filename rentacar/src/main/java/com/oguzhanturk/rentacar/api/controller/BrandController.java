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

import com.oguzhanturk.rentacar.business.abstracts.BrandService;
import com.oguzhanturk.rentacar.business.dtos.BrandDto;
import com.oguzhanturk.rentacar.business.dtos.ListBrandDto;
import com.oguzhanturk.rentacar.business.request.CreateBrandRequest;
import com.oguzhanturk.rentacar.business.request.DeleteBrandRequest;
import com.oguzhanturk.rentacar.business.request.UpdateBrandRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

	private final BrandService brandService;

	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@GetMapping("/getall")
	public DataResult<List<ListBrandDto>> getAll() {
		return brandService.getAll();
	}

	@GetMapping("/get/{id}")
	public DataResult<BrandDto> get(@RequestParam int id) throws BusinessException {
		return brandService.getById(id);
	}

	@PostMapping("/save")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) throws BusinessException {
		return brandService.add(createBrandRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest) throws BusinessException {
		return brandService.delete(deleteBrandRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) throws BusinessException {
		return brandService.update(updateBrandRequest);
	}
}
