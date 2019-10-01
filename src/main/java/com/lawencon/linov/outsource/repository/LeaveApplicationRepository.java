package com.lawencon.linov.outsource.repository;

import com.lawencon.linov.outsource.model.leave.LeaveApplication;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeaveApplicationRepository extends PagingAndSortingRepository<LeaveApplication, Long> {
}
