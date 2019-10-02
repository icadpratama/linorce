package com.lawencon.linov.outsource.controller.approval;

import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.approval.BusinessTrip;
import com.lawencon.linov.outsource.payload.response.OutsourceResponse;
import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.BusinessTripService;
import com.lawencon.linov.outsource.service.ImageService;
import com.lawencon.linov.outsource.service.UserService;
import com.lawencon.linov.outsource.util.CommonUtil;
import com.lawencon.linov.outsource.util.PageAndSort;
import com.lawencon.linov.outsource.util.StatusName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.Timestamp;
import java.util.Objects;

@RestController
@RequestMapping("/approval/business_trip")
public class BusinessTripController {

    private final BusinessTripService businessTripService;
    private final ImageService imageService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(BusinessTripController.class);

    public BusinessTripController(BusinessTripService businessTripService, ImageService imageService, UserService userService) {
        this.businessTripService = businessTripService;
        this.imageService = imageService;
        this.userService = userService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_HR')")
    public ResponseEntity createBusinessTrip(@RequestParam(name = "start") Timestamp start,
                           @RequestParam(name = "end") Timestamp end,
                           @RequestParam(value = "reason") String reason,
                           @RequestParam(name = "file") MultipartFile file,
                           @RequestParam(name = "approver") Long userId,
                           @CurrentUser UserPrincipal currentUser) {

        @NotNull
        URI location = null;

        try {
            String contentType = file.getContentType();
            InputStream data = file.getInputStream();
            String objectName = currentUser.getUsername() + "/" + CommonUtil.trimSpace(Objects.requireNonNull(file.getOriginalFilename()));
            Long size = file.getSize();
            String bucketName = "item-request";

            CommonUtil.fileUpload(bucketName,  objectName, data, size, contentType);
            Image image = imageService.uploadImage(new Image(objectName, bucketName, size, contentType));

            BusinessTrip bt = new BusinessTrip(start, end, reason, image, userId, StatusName.PENDING);
            BusinessTrip request = businessTripService.requestBusinessTrip(bt);

            location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(request.getId()).toUri();
        } catch (IOException e){
            logger.error(e.getMessage());
        }

        assert location != null;
        return ResponseEntity.created(location)
                .body(new OutsourceResponse(true, "Business Trip Created Successfully"));
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity getBusinessTrip(@CurrentUser UserPrincipal currentUser,
                                         @Valid PageAndSort model){
        Page result = businessTripService.getAllBusinessTrip(model);
        return ResponseEntity.ok(result);
    }
}
