package com.oguzhanturk.rentacar.api.controller;

import java.util.List;

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

@RestController
@RequestMapping("/api/brands")
public class BrandController {

	private final BrandService brandService;

	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@GetMapping("/getall")
	public List<ListBrandDto> getAll() {
		return brandService.getAll();
	}

	@GetMapping("/get")
	public BrandDto get(@RequestParam int id) {
		return brandService.getById(id);
	}

	@PostMapping("/save")
	public void add(@RequestBody CreateBrandRequest createBrandRequest) {
		brandService.add(createBrandRequest);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		brandService.delete(deleteBrandRequest);
	}

	@PutMapping("/update")
	public void update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		brandService.update(updateBrandRequest);
	}
}
