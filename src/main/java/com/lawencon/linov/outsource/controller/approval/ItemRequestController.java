package com.lawencon.linov.outsource.controller.approval;

import com.lawencon.linov.outsource.exception.ResourceNotFoundException;
import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.approval.ItemRequest;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.payload.request.ItemReqRequest;
import com.lawencon.linov.outsource.payload.response.ItemRequestResponse;
import com.lawencon.linov.outsource.payload.response.OutsourceResponse;
import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.ImageService;
import com.lawencon.linov.outsource.service.ItemRequestService;
import com.lawencon.linov.outsource.service.UserService;
import com.lawencon.linov.outsource.util.CommonUtil;
import com.lawencon.linov.outsource.util.PageAndSort;
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
import java.util.Objects;

@RestController
@RequestMapping("/approval/item_request")
public class ItemRequestController {

    private final ItemRequestService requestService;
    private final ImageService imageService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ItemRequestController.class);

    public ItemRequestController(ItemRequestService requestService, ImageService imageService, UserService userService) {
        this.requestService = requestService;
        this.imageService = imageService;
        this.userService = userService;
    }

    // Get all item requests
    @CrossOrigin(origins = "*")
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity getItemRequest(@CurrentUser UserPrincipal currentUser,
                                         @Valid PageAndSort model) {
        Page result = requestService.getAllItemRequests(model);
        return ResponseEntity.ok(result);
    }

    // Create an item request
    @CrossOrigin(origins = "*")
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_HR')")
    public ResponseEntity createItemRequest(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(value = "quantity") Integer quantity,
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

            User approver = userService.userById(userId).orElseThrow(
                    () -> new ResourceNotFoundException("User","id", userId)
            );

            ItemReqRequest itemRequest = new ItemReqRequest(name, quantity, description, image, userId);
            ItemRequest request = requestService.createItemRequest(itemRequest, approver);

            location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{itemRequestId}")
                    .buildAndExpand(request.getId()).toUri();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        assert location != null;
        return ResponseEntity.created(location)
                .body(new OutsourceResponse(true, "Item Request Created Successfully"));
    }

    // Get item request detail
    @GetMapping("/{id}")
    public ResponseEntity getItemRequestById(@CurrentUser UserPrincipal currentUser, @PathVariable Long id){
        ItemRequestResponse result = requestService.getItemRequestById(id, currentUser);
        return ResponseEntity.ok(result);
    }

    // Update item request
    @PutMapping
    public ResponseEntity updateItemRequest(@CurrentUser UserPrincipal currentUser){
        return null;
    }

    // Delete item request
    @DeleteMapping
    public ResponseEntity removeItemRequest(@PathVariable("id") Long id){
        requestService.delete(id);
        return null;
    }
}
