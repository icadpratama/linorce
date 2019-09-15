package com.lawencon.linov.outsource.controller.attendance;

import com.lawencon.linov.outsource.model.attendance.Absence;
import com.lawencon.linov.outsource.payload.request.AbsenceRequest;
import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.AbsenceService;
import com.lawencon.linov.outsource.util.AbsenceType;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/absence")
public class AbsenceController {

    private final AbsenceService absenceService;
    private final Logger logger = LoggerFactory.getLogger(AbsenceController.class);

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_HR')")
    public ResponseEntity historyCheckInByUser(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(absenceService.getAbsenceById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_ADMIN')")
    public ResponseEntity allHistoryCheckIn(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "5") Integer size,
                                            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                            @RequestParam(value = "field", defaultValue = "location") String column){
        PageAndSort model = new PageAndSort();
        model.setPage(page);
        model.setSize(size);
        model.setDirection(direction);
        model.setColumn(column);

        Page result = absenceService.getAllAbsences(model);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/checkin")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity checkIn(@Valid @RequestBody AbsenceRequest absenceRequest,
            @CurrentUser UserPrincipal currentUser) {
        Absence absence = new Absence(absenceRequest.getLocation(), absenceRequest.getProjectName(), AbsenceType.CHECK_IN);
        Absence result = absenceService.checkIn(absence);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/checkout")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity checkOut(@Valid @RequestBody AbsenceRequest absenceRequest,
                                  @CurrentUser UserPrincipal currentUser) {
        Absence absence = new Absence(absenceRequest.getLocation(), absenceRequest.getProjectName(), AbsenceType.CHECK_OUT);
        Absence result = absenceService.checkIn(absence);
        return ResponseEntity.ok(result);
    }
}
