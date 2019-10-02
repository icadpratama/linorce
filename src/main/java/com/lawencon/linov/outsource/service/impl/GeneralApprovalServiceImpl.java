package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.model.approval.GeneralApproval;
import com.lawencon.linov.outsource.repository.GeneralApprovalRepository;
import com.lawencon.linov.outsource.service.GeneralApprovalService;
import com.lawencon.linov.outsource.util.PageAndSort;
import com.lawencon.linov.outsource.util.PagingAndSorting;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GeneralApprovalServiceImpl implements GeneralApprovalService {
    private final GeneralApprovalRepository repository;

    public GeneralApprovalServiceImpl(GeneralApprovalRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page getAllItemRequests(PageAndSort model) {
        return repository.findAll(PagingAndSorting.paging(model));
    }

    @Override
    public GeneralApproval createGeneralApproval(GeneralApproval ga) {
        return repository.save(ga);
    }
}
