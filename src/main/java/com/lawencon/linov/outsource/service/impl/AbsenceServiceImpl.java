package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.model.attendance.Absence;
import com.lawencon.linov.outsource.repository.AbsenceRepository;
import com.lawencon.linov.outsource.service.AbsenceService;
import com.lawencon.linov.outsource.util.PageAndSort;
import com.lawencon.linov.outsource.util.PagingAndSorting;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<Absence> getAllAbsences(PageAndSort model) {
        return absenceRepository.findAll(PagingAndSorting.paging(model));
    }

    @Override
    public Optional<Absence> getAbsenceById(Long id) {
        return absenceRepository.findById(id);
    }

    @Override
    public List<Absence> getAllAbsencePerMonth(Long id, Integer month, Integer year) {
        return absenceRepository.findAllByCreatedByAndMonthAndYear(id, month, year);
    }
}
