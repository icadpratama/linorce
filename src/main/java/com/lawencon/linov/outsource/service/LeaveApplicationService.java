package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.payload.response.ObjectResponse;
import com.lawencon.linov.outsource.util.PageAndSort;

import java.util.Map;

public interface LeaveApplicationService {

    Map<String, ObjectResponse> getAllLeaveApplications(PageAndSort model);
}
