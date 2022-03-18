package com.oguzhanturk.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.oguzhanturk.rentacar.business.dtos.invoice.InvoiceDto;
import com.oguzhanturk.rentacar.business.dtos.invoice.ListInvoiceDto;
import com.oguzhanturk.rentacar.business.request.invoice.CreateInvoiceRequest;
import com.oguzhanturk.rentacar.business.request.invoice.DeleteInvoiceRequest;
import com.oguzhanturk.rentacar.business.request.invoice.UpdateInvoiceRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;

public interface InvoiceService {

	DataResult<List<ListInvoiceDto>> getAll();

	Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException;

	DataResult<InvoiceDto> getById(int id) throws BusinessException;

	Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException;

	Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException;
//	DataResult<List<ListInvoiceDto>> getAllBetweenTwoDates(DateInvoiceRequest dateInvoiceRequest);

	DataResult<List<ListInvoiceDto>> getAllBetweenTwoDates(LocalDate fromDate, LocalDate toDate);
}
