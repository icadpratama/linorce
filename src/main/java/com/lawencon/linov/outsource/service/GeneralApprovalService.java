package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.model.approval.GeneralApproval;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.springframework.data.domain.Page;

public interface GeneralApprovalService {

    Page getAllItemRequests(PageAndSort model);
    GeneralApproval createGeneralApproval(GeneralApproval ga);
}
