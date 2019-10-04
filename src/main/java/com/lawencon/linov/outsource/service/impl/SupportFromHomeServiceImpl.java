package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.repository.SupportFromHomeRepository;
import com.lawencon.linov.outsource.service.SupportFromHomeService;
import com.lawencon.linov.outsource.util.PageAndSort;
import com.lawencon.linov.outsource.util.PagingAndSorting;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SupportFromHomeServiceImpl implements SupportFromHomeService {

    private final SupportFromHomeRepository repository;

    public SupportFromHomeServiceImpl(SupportFromHomeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page getAllSupportFromHome(PageAndSort model) {
        return repository.findAll(PagingAndSorting.paging(model));
    }
}
