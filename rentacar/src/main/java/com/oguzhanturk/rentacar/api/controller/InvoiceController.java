package com.oguzhanturk.rentacar.api.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oguzhanturk.rentacar.business.abstracts.InvoiceService;
import com.oguzhanturk.rentacar.business.dtos.invoice.InvoiceDto;
import com.oguzhanturk.rentacar.business.dtos.invoice.ListInvoiceDto;
import com.oguzhanturk.rentacar.business.request.invoice.CreateInvoiceRequest;
import com.oguzhanturk.rentacar.business.request.invoice.DeleteInvoiceRequest;
import com.oguzhanturk.rentacar.business.request.invoice.UpdateInvoiceRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	private InvoiceService invoiceService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@GetMapping("/getAll")
	public DataResult<List<ListInvoiceDto>> getAll() {
		return this.invoiceService.getAll();
	}

	@GetMapping("/get/{id}")
	public DataResult<InvoiceDto> getById(@RequestParam int id) throws BusinessException {
		return this.invoiceService.getById(id);
	}

	@PostMapping("/create")
	public Result add(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest) throws BusinessException {
		return invoiceService.add(createInvoiceRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException {
		return invoiceService.update(updateInvoiceRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException {
		return invoiceService.delete(deleteInvoiceRequest);
	}

	@GetMapping("/getAllBetweenTwoDates")
	public DataResult<List<ListInvoiceDto>> getAllBetweenTwoDates(
			@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
		return invoiceService.getAllBetweenTwoDates(fromDate, toDate);
	}

}
