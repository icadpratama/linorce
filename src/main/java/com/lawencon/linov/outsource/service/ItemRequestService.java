package com.lawencon.linov.outsource.service;


import com.lawencon.linov.outsource.util.PageAndSort;
import org.springframework.data.domain.Page;

public interface ItemRequestService {

    Page getAllItemRequests(PageAndSort model);
}
