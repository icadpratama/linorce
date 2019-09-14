package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.exception.ResourceNotFoundException;
import com.lawencon.linov.outsource.model.approval.ItemRequest;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.payload.request.ItemReqRequest;
import com.lawencon.linov.outsource.payload.response.ItemRequestResponse;
import com.lawencon.linov.outsource.payload.response.UserSummary;
import com.lawencon.linov.outsource.repository.ItemRequestRepository;
import com.lawencon.linov.outsource.repository.UserRepository;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.ItemRequestService;
import com.lawencon.linov.outsource.util.PageAndSort;
import com.lawencon.linov.outsource.util.PagingAndSorting;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ItemRequestServiceImpl implements ItemRequestService {

    private final ItemRequestRepository requestRepository;
    private final UserRepository userRepository;

    public ItemRequestServiceImpl(ItemRequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page getAllItemRequests(PageAndSort model) {
        return requestRepository.findAll(PagingAndSorting.paging(model));
    }

    @Override
    public ItemRequest createItemRequest(ItemReqRequest request) {
        ItemRequest item = new ItemRequest();

        item.setName(request.getName());
        item.setQuantity(request.getQuantity());
        item.setDetails(request.getDetails());
        item.setDocuments(request.getDocuments());

        return requestRepository.save(item);
    }

    @Override
    public ItemRequestResponse getItemRequestById(Long id, UserPrincipal currentUser) {
        ItemRequest request = requestRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Item Request","id", id)
        );

        User creator = userRepository.findById(request.getCreatedBy()).orElseThrow(
                () -> new ResourceNotFoundException("User","id", request.getCreatedBy())
        );

        UserSummary summary = new UserSummary(creator.getId(), creator.getUsername(), creator.getFirstName(), creator.getLastName());

        ItemRequestResponse response = new ItemRequestResponse();
        response.setId(request.getId());
        response.setQuantity(request.getQuantity());
        response.setName(request.getName());
        response.setDetails(request.getDetails());
        response.setDocument(request.getDocuments());
        response.setCreatedBy(summary);
        response.setCreationDateTime(request.getCreatedAt());

        return response;
    }

    @Override
    public Long sumOfRequest(Long id) {
        return requestRepository.countByCreatedBy(id);
    }
}
