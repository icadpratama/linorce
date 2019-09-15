package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.model.attendance.Absence;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AbsenceService {

    Absence checkIn(Absence absence);
    Page<Absence> getAllAbsences(PageAndSort model);
    Optional<Absence> getAbsenceById(Long id);
}
