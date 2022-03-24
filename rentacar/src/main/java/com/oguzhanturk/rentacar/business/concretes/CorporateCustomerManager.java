package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.CorporateCustomerService;
import com.oguzhanturk.rentacar.business.constants.Messages;
import com.oguzhanturk.rentacar.business.dtos.corporateCustomer.CorporateCustomerDto;
import com.oguzhanturk.rentacar.business.dtos.corporateCustomer.ListCorporateCustomerDto;
import com.oguzhanturk.rentacar.business.request.corporateCustomer.CreateCorporateCustomerRequest;
import com.oguzhanturk.rentacar.business.request.corporateCustomer.DeleteCorporateCustomerRequest;
import com.oguzhanturk.rentacar.business.request.corporateCustomer.UpdateCorporateCustomerRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.CorporateCustomerDao;
import com.oguzhanturk.rentacar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<CorporateCustomerDto> getById(int id) throws BusinessException {

		CorporateCustomer corporateCustomer = corporateCustomerDao.getById(id);
		CorporateCustomerDto corporateCustomerDto = modelMapperService.forDto().map(corporateCustomer,
				CorporateCustomerDto.class);
		return new SuccessDataResult<CorporateCustomerDto>(corporateCustomerDto, Messages.CORPORATE_CUSTOMER_FOUND);
	}

	@Override
	public Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {

		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		corporateCustomerDao.save(corporateCustomer);

		return new SuccessResult(Messages.CORPORATE_CUSTOMER_ADD);
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException {

		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		corporateCustomerDao.save(corporateCustomer);

		return new SuccessResult(Messages.CORPORATE_CUSTOMER_UPDATE);
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException {
		corporateCustomerDao.deleteById(deleteCorporateCustomerRequest.getId());
		return new SuccessResult(Messages.CORPORATE_CUSTOMER_DELETE);
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> getAllSorted(Direction direction) throws BusinessException {

		Sort sort = Sort.by(direction, "corporateName");
		List<CorporateCustomer> corporateCustomers = corporateCustomerDao.findAll(sort);
		List<ListCorporateCustomerDto> listCorporateCustomerDtos = corporateCustomers.stream().map(
				corporateCustomer -> modelMapperService.forDto().map(corporateCustomer, ListCorporateCustomerDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCorporateCustomerDto>>(listCorporateCustomerDtos,
				Messages.CORPORATE_CUSTOMER_LIST);
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<CorporateCustomer> corporateCustomers = corporateCustomerDao.findAll(pageable).getContent();
		List<ListCorporateCustomerDto> listCorporateCustomerDtos = corporateCustomers.stream()
				.map(corporateCustomer -> modelMapperService.forDto().map(corporateCustomers,
						ListCorporateCustomerDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCorporateCustomerDto>>(listCorporateCustomerDtos,
				Messages.CORPORATE_CUSTOMER_LIST);
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> listAll() throws BusinessException {

		List<CorporateCustomer> corporateCustomers = corporateCustomerDao.findAll();
		List<ListCorporateCustomerDto> listCorporateCustomerDtos = corporateCustomers.stream().map(
				corporateCustomer -> modelMapperService.forDto().map(corporateCustomer, ListCorporateCustomerDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCorporateCustomerDto>>(listCorporateCustomerDtos,
				Messages.CORPORATE_CUSTOMER_LIST);
	}

}