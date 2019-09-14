package com.lawencon.linov.outsource.controller.attendance;

import com.lawencon.linov.outsource.model.attendance.Absence;
import com.lawencon.linov.outsource.payload.request.AbsenceRequest;
import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.AbsenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/absence")
public class AbsenceController {

    private final AbsenceService absenceService;
    private final Logger logger = LoggerFactory.getLogger(AbsenceController.class);

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_HR', 'ROLE_ADMIN')")
    public ResponseEntity historyCheckInByUser(@RequestParam("id") Long id){
        return null;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    public ResponseEntity allHistoryCheckIn(){
        return null;
    }

    @PostMapping("/checkin")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity checkIn(@Valid @RequestBody AbsenceRequest absenceRequest,
            @CurrentUser UserPrincipal currentUser) {
        Absence absence = new Absence(absenceRequest.getLocation(), absenceRequest.getProjectName());
        Absence result = absenceService.checkIn(absence);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/checkout")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity checkOut(@Valid @RequestBody AbsenceRequest absenceRequest,
                                  @CurrentUser UserPrincipal currentUser) {
        Absence absence = new Absence(absenceRequest.getLocation(), absenceRequest.getProjectName());
        Absence result = absenceService.checkIn(absence);
        return ResponseEntity.ok(result);
    }
}
