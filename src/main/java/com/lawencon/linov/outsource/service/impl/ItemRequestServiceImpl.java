package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.repository.ItemRequestRepository;
import com.lawencon.linov.outsource.service.ItemRequestService;
import com.lawencon.linov.outsource.util.PageAndSort;
import com.lawencon.linov.outsource.util.PagingAndSorting;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ItemRequestServiceImpl implements ItemRequestService {

    private final ItemRequestRepository requestRepository;

    public ItemRequestServiceImpl(ItemRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Page getAllItemRequests(PageAndSort model) {
        return requestRepository.findAll(PagingAndSorting.paging(model));
    }
}
