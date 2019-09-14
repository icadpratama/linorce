package com.lawencon.linov.outsource.repository;

import com.lawencon.linov.outsource.model.attendance.Absence;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AbsenceRepository extends PagingAndSortingRepository<Absence, Long> {
}
