package com.lawencon.linov.outsource.controller.approval;

import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.SupportFromHomeService;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/approval/support_from_home")
public class SupportFromHomeController {

    private final SupportFromHomeService service;
    private static final Logger logger = LoggerFactory.getLogger(SupportFromHomeController.class);

    public SupportFromHomeController(SupportFromHomeService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity getAllSupport(@CurrentUser UserPrincipal currentUser,
                                        @Valid PageAndSort model){

        Page result = service.getAllSupportFromHome(model);
        return ResponseEntity.ok(result);
    }
}
