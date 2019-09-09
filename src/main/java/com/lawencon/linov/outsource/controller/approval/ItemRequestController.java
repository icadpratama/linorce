package com.lawencon.linov.outsource.controller.approval;

import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.ItemRequestService;
import com.lawencon.linov.outsource.service.UserService;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item_request")
public class ItemRequestController {

    private final ItemRequestService requestService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ItemRequestController.class);

    public ItemRequestController(ItemRequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getItemRequest(@CurrentUser UserPrincipal currentUser,
                                         @RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "size") Integer size,
                                         @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                         @RequestParam(value = "field") String column){

        PageAndSort model = new PageAndSort();
        model.setPage(page);
        model.setSize(size);
        model.setDirection(direction);
        model.setColumn(column);

        Page result = requestService.getAllItemRequests(model);
        return ResponseEntity.ok(result);
    }
}
