package com.lawencon.linov.outsource.controller.approval;

import com.lawencon.linov.outsource.model.approval.ItemRequest;
import com.lawencon.linov.outsource.payload.request.ItemReqRequest;
import com.lawencon.linov.outsource.payload.response.ItemRequestResponse;
import com.lawencon.linov.outsource.payload.response.OutsourceResponse;
import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@RestController
@RequestMapping("/approval/item_request")
public class ItemRequestController {

    private final ItemRequestService requestService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ItemRequestController.class);

    public ItemRequestController(ItemRequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getItemRequest(@CurrentUser UserPrincipal currentUser,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "5") Integer size,
                                         @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                         @RequestParam(value = "field", defaultValue = "name") String column){

        PageAndSort model = new PageAndSort();
        model.setPage(page);
        model.setSize(size);
        model.setDirection(direction);
        model.setColumn(column);

        Page result = requestService.getAllItemRequests(model);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_HR')")
    public ResponseEntity createPoll(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(value = "quantity") Integer quantity,
            @RequestParam(name = "file") MultipartFile file,
            @CurrentUser UserPrincipal currentUser) {

        URI location = null;

        try {
            String contentType = file.getContentType();
            InputStream data = file.getInputStream();
            String objectName = currentUser.getUsername() + "/" + file.getOriginalFilename();
            Long size = file.getSize();

            ItemReqRequest itemRequest = new ItemReqRequest(name, quantity, description, objectName);

            ItemRequest request = requestService.createItemRequest(itemRequest);

            CommonUtil.fileUpload("item-request",  objectName, data, size, contentType);

            location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{itemRequestId}")
                    .buildAndExpand(request.getId()).toUri();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.created(location)
                .body(new OutsourceResponse(true, "Item Request Created Successfully"));
    }

    @GetMapping("/{itemRequestId}")
    public ResponseEntity getItemRequestById(@CurrentUser UserPrincipal currentUser, @PathVariable Long id){
        ItemRequestResponse result = requestService.getItemRequestById(id, currentUser);
        return ResponseEntity.ok(result);
    }
}
