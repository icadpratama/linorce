package com.lawencon.linov.outsource.controller.approval;

import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.GeneralApprovalService;
import com.lawencon.linov.outsource.service.ImageService;
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
@RequestMapping("/approval/general_approval")
public class GeneralApprovalController {

    private final GeneralApprovalService approvalService;
    private final ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(GeneralApprovalController.class);

    public GeneralApprovalController(GeneralApprovalService approvalService, ImageService imageService) {
        this.approvalService = approvalService;
        this.imageService = imageService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity getBusinessTrip(@CurrentUser UserPrincipal currentUser,
                                          @Valid PageAndSort model){
//        Page result = approvalService.(model);
//        return ResponseEntity.ok(result);
        return null;
    }
}
