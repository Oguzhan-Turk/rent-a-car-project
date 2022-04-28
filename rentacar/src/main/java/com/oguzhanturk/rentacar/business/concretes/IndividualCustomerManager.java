package com.oguzhanturk.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.oguzhanturk.rentacar.business.abstracts.IndividualCustomerService;
import com.oguzhanturk.rentacar.business.constants.Messages;
import com.oguzhanturk.rentacar.business.dtos.individualCustomer.IndividualCustomerDto;
import com.oguzhanturk.rentacar.business.dtos.individualCustomer.ListIndividualCustomerDto;
import com.oguzhanturk.rentacar.business.request.individualCustomer.CreateIndividualCustomerRequest;
import com.oguzhanturk.rentacar.business.request.individualCustomer.DeleteIndividualCustomerRequest;
import com.oguzhanturk.rentacar.business.request.individualCustomer.UpdateIndividualCustomerRequest;
import com.oguzhanturk.rentacar.core.utilities.exceptions.BusinessException;
import com.oguzhanturk.rentacar.core.utilities.mapping.ModelMapperService;
import com.oguzhanturk.rentacar.core.utilities.results.DataResult;
import com.oguzhanturk.rentacar.core.utilities.results.Result;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessDataResult;
import com.oguzhanturk.rentacar.core.utilities.results.SuccessResult;
import com.oguzhanturk.rentacar.dataAccess.abstracts.IndividualCustomerDao;
import com.oguzhanturk.rentacar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

    private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
                                     ModelMapperService modelMapperService) {
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<IndividualCustomerDto> getById(int id) throws BusinessException {

        IndividualCustomer individualCustomer = individualCustomerDao.getById(id);
        IndividualCustomerDto individualCustomerDto = modelMapperService.forDto().map(individualCustomer,
                IndividualCustomerDto.class);
        return new SuccessDataResult<IndividualCustomerDto>(individualCustomerDto, Messages.INDIVIDUAL_CUSTOMER_FOUND);
    }

    @Override
    public Result create(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException {

        IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualCustomerRequest,
                IndividualCustomer.class);
        individualCustomerDao.save(individualCustomer);

        return new SuccessResult(Messages.INDIVIDUAL_CUSTOMER_ADD);
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException {
        IndividualCustomer individualCustomer = modelMapperService.forRequest().map(updateIndividualCustomerRequest,
                IndividualCustomer.class);
        individualCustomerDao.save(individualCustomer);

        return new SuccessResult(Messages.INDIVIDUAL_CUSTOMER_UPDATE);
    }

    @Override
    public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException {
        individualCustomerDao.deleteById(deleteIndividualCustomerRequest.getId());
        return new SuccessResult(Messages.INDIVIDUAL_CUSTOMER_DELETE);
    }

    @Override
    public DataResult<List<ListIndividualCustomerDto>> getAllSorted(Direction direction) throws BusinessException {
        Sort sort = Sort.by(direction, "firstname");
        List<IndividualCustomer> individualCustomers = individualCustomerDao.findAll(sort);
        List<ListIndividualCustomerDto> listIndividualCustomerDtos = individualCustomers.stream()
                .map(individualCustomer -> modelMapperService.forDto().map(individualCustomer,
                        ListIndividualCustomerDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListIndividualCustomerDto>>(listIndividualCustomerDtos,
                Messages.INDIVIDUAL_CUSTOMER_LIST);
    }

    @Override
    public DataResult<List<ListIndividualCustomerDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<IndividualCustomer> individualCustomers = individualCustomerDao.findAll(pageable).getContent();
        List<ListIndividualCustomerDto> listIndividualCustomerDtos = individualCustomers.stream()
                .map(individualCustomer -> modelMapperService.forDto().map(individualCustomer,
                        ListIndividualCustomerDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListIndividualCustomerDto>>(listIndividualCustomerDtos,
                Messages.INDIVIDUAL_CUSTOMER_LIST);
    }

    @Override
    public DataResult<List<ListIndividualCustomerDto>> listAll() throws BusinessException {
        List<IndividualCustomer> individualCustomers = individualCustomerDao.findAll();
        List<ListIndividualCustomerDto> listIndividualCustomerDtos = individualCustomers.stream()
                .map(individualCustomer -> modelMapperService.forDto().map(individualCustomer,
                        ListIndividualCustomerDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListIndividualCustomerDto>>(listIndividualCustomerDtos,
                Messages.INDIVIDUAL_CUSTOMER_LIST);
    }

}
