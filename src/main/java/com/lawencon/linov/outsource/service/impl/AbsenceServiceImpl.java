package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.model.attendance.Absence;
import com.lawencon.linov.outsource.repository.AbsenceRepository;
import com.lawencon.linov.outsource.service.AbsenceService;
import org.springframework.stereotype.Service;

@Service
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRepository absenceRepository;

    public AbsenceServiceImpl(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    @Override
    public Absence checkIn(Absence absence) {
        return absenceRepository.save(absence);
    }
}
