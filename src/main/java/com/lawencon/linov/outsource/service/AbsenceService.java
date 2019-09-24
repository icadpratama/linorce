package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.model.attendance.Absence;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface AbsenceService {

    Absence checkIn(Absence absence);
    Page<Absence> getAllAbsences(PageAndSort model);
    Optional<Absence> getAbsenceById(Long id);
    List<Absence> getAllAbsencePerMonth(Long id, Integer month, Integer year);
    Optional<Absence> getAbsenceByIdContaining(Long currentUser, Instant start, Instant end);
}
