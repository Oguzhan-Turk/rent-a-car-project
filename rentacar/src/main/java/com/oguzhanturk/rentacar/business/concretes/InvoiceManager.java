package com.oguzhanturk.rentacar.business.concretes;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.InvoiceService;
import com.oguzhanturk.rentacar.business.abstracts.RentalService;
import com.oguzhanturk.rentacar.business.dtos.invoice.InvoiceDto;
import com.oguzhanturk.rentacar.business.dtos.invoice.ListInvoiceDto;
import com.oguzhanturk.rentacar.business.dtos.rental.RentalDto;
import com.oguzhanturk.rentacar.business.request.invoice.CreateInvoiceRequest;
import com.oguzhanturk.rentacar.business.request.invoice.DeleteInvoiceRequest;
import com.oguzhanturk.rentacar.business.request.invoice.UpdateInvoiceRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.InvoiceDao;
import com.oguzhanturk.rentacar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService {
	private final ModelMapperService modelMapperService;
	private final InvoiceDao invoiceDao;
	private final RentalService rentalService;

	@Autowired
	public InvoiceManager(ModelMapperService modelMapperService, InvoiceDao invoiceDao, RentalService rentalService) {
		this.modelMapperService = modelMapperService;
		this.invoiceDao = invoiceDao;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getAll() {
		List<Invoice> result = invoiceDao.findAll();
		List<ListInvoiceDto> response = result.stream()
				.map(invoice -> modelMapperService.forDto().map(invoice, ListInvoiceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException {
		Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		RentalDto rentalDto = rentalService.getById(createInvoiceRequest.getRentId()).getData();

//		invoice.setTotalRentDay(calculateTotalRentDate(invoice.getRental().getRentId()));
//		invoice.setTotalPrice(calculateTotalPrice(invoice.getRental().getRentId()));

		invoiceDao.save(invoice);
		return new SuccessResult("Invoice.Add");
	}

	@Override
	public DataResult<InvoiceDto> getById(int id) throws BusinessException {
		checkIfExistsById(id);
		Invoice result = invoiceDao.getById(id);

		InvoiceDto response = modelMapperService.forDto().map(result, InvoiceDto.class);

		return new SuccessDataResult<InvoiceDto>(response);
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException {
		checkIfExistsById(updateInvoiceRequest.getInvoiceId());

		Invoice invoice = modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
//		invoice.setInvoiceNo(updateInvoiceRequest.getInvoiceNo());

		invoiceDao.save(invoice);
		return new SuccessResult("Invoice.Update");
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException {
		checkIfExistsById(deleteInvoiceRequest.getInvoiceId());
		invoiceDao.deleteById(deleteInvoiceRequest.getInvoiceId());
		return new SuccessResult("Invoice.Delete");
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getAllBetweenTwoDates(LocalDate fromDate, LocalDate toDate) {

		List<Invoice> result = invoiceDao.findByBillingDateBetween(fromDate, toDate);
		List<ListInvoiceDto> response = result.stream()
				.map(invoice -> modelMapperService.forDto().map(invoice, ListInvoiceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	}

	private BigDecimal calculateTotalPrice(int rentalId) throws BusinessException {
		RentalDto rental = rentalService.getById(rentalId).getData();
		BigDecimal totalPrice = rental.getRentalDailyPrice()
				.multiply(BigDecimal.valueOf(calculateTotalRentDate(rentalId)));
		return totalPrice;

	}

	private long calculateTotalRentDate(int rentalId) throws BusinessException {
		RentalDto rental = rentalService.getById(rentalId).getData();
		long totalRentDay = Duration.between(rental.getRentDate(), rental.getReturnDate()).toDays();

		return totalRentDay;
	}

	private void checkIfExistsById(int invoiceNo) throws BusinessException {
		if (!invoiceDao.existsById(invoiceNo)) {
			throw new BusinessException("The invoice with id : " + invoiceNo + " was not found!");
		}
	}

}
