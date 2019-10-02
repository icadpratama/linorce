package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.model.approval.BusinessTrip;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BusinessTripService {

    Page getAllBusinessTrip(PageAndSort model);
    BusinessTrip requestBusinessTrip(BusinessTrip bt);
    Optional<BusinessTrip> getBusinessTripById(Long id);
}
