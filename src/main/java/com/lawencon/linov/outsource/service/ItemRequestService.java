package com.lawencon.linov.outsource.service;


import com.lawencon.linov.outsource.model.approval.ItemRequest;
import com.lawencon.linov.outsource.payload.request.ItemReqRequest;
import com.lawencon.linov.outsource.payload.response.ItemRequestResponse;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.springframework.data.domain.Page;

public interface ItemRequestService {

    Page getAllItemRequests(PageAndSort model);
    ItemRequest createItemRequest(ItemReqRequest request);
    ItemRequestResponse getItemRequestById(Long id, UserPrincipal currentUser);
}
