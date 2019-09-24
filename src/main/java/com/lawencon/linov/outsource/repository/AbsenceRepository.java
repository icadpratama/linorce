package com.lawencon.linov.outsource.repository;

import com.lawencon.linov.outsource.model.attendance.Absence;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AbsenceRepository extends PagingAndSortingRepository<Absence, Long> {
    List<Absence> findAllByCreatedByAndMonthAndYear(Long id, Integer month, Integer year);
}
