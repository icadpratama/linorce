package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.model.approval.BusinessTrip;
import com.lawencon.linov.outsource.repository.BusinessTripRepository;
import com.lawencon.linov.outsource.service.BusinessTripService;
import com.lawencon.linov.outsource.util.PageAndSort;
import com.lawencon.linov.outsource.util.PagingAndSorting;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessTripServiceImpl implements BusinessTripService {
    private final BusinessTripRepository repository;

    public BusinessTripServiceImpl(BusinessTripRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page getAllBusinessTrip(PageAndSort model) {
        return repository.findAll(PagingAndSorting.paging(model));
    }

    @Override
    public BusinessTrip requestBusinessTrip(BusinessTrip bt) {
        return repository.save(bt);
    }

    @Override
    public Optional<BusinessTrip> getBusinessTripById(Long id) {
        return repository.findById(id);
    }
}
