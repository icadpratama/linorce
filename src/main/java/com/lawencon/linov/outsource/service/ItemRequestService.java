package com.lawencon.linov.outsource.service;


import com.lawencon.linov.outsource.model.approval.ItemRequest;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.payload.request.ItemReqRequest;
import com.lawencon.linov.outsource.payload.response.ItemRequestResponse;
import com.lawencon.linov.outsource.payload.response.ObjectResponse;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ItemRequestService {

    ItemRequest createItemRequest(ItemReqRequest request, User approver);
    ItemRequestResponse getItemRequestById(Long id, UserPrincipal currentUser);
    Long sumOfRequest(Long id);
    void delete(Long id);
    Map<String, ObjectResponse> getAllItemRequests(PageAndSort model);
}
