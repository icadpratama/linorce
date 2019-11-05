package com.lawencon.linov.outsource.controller.leave;

import com.lawencon.linov.outsource.payload.response.ObjectResponse;
import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.LeaveApplicationService;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/leave/leave_application")
public class LeaveApplicationController {

    private final LeaveApplicationService service;

    private static final Logger logger = LoggerFactory.getLogger(LeaveApplicationController.class);

    public LeaveApplicationController(LeaveApplicationService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_HR','ROLE_ADMIN')")
    public ResponseEntity showAllLeaveApplication(@CurrentUser UserPrincipal currentUser, PageAndSort model){
        Map<String, ObjectResponse> responseMap = service.getAllLeaveApplications(model);
        return ResponseEntity.ok(responseMap);
    }
}
