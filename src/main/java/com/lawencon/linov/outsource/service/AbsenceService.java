package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.model.attendance.Absence;

public interface AbsenceService {

    Absence checkIn(Absence absence);
}
