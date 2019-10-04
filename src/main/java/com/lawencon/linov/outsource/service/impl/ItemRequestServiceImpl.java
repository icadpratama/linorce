package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.exception.ResourceNotFoundException;
import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.approval.ItemRequest;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.payload.request.ItemReqRequest;
import com.lawencon.linov.outsource.payload.response.ImageSummary;
import com.lawencon.linov.outsource.payload.response.ItemRequestResponse;
import com.lawencon.linov.outsource.payload.response.ObjectResponse;
import com.lawencon.linov.outsource.payload.response.UserSummary;
import com.lawencon.linov.outsource.repository.ImageRepository;
import com.lawencon.linov.outsource.repository.ItemRequestRepository;
import com.lawencon.linov.outsource.repository.UserRepository;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.ItemRequestService;
import com.lawencon.linov.outsource.util.PageAndSort;
import com.lawencon.linov.outsource.util.PagingAndSorting;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ItemRequestServiceImpl implements ItemRequestService {

    private final ItemRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public ItemRequestServiceImpl(ItemRequestRepository requestRepository, UserRepository userRepository, ImageRepository imageRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public ItemRequest createItemRequest(ItemReqRequest request, User approver) {
        ItemRequest item = new ItemRequest();

        item.setName(request.getName());
        item.setQuantity(request.getQuantity());
        item.setDetails(request.getDetails());
        item.setImage(request.getImage());
        item.setApprover(approver);

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
        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getFirstName(), creator.getLastName());

        User approver = userRepository.findById(request.getApprover().getId()).orElseThrow(
                () -> new ResourceNotFoundException("User","id", request.getCreatedBy())
        );
        UserSummary approverSummary = new UserSummary(approver.getId(), approver.getUsername(), approver.getFirstName(), approver.getLastName());

        ItemRequestResponse response = new ItemRequestResponse();
        response.setId(request.getId());
        response.setQuantity(request.getQuantity());
        response.setName(request.getName());
        response.setDetails(request.getDetails());
        response.setCreatedBy(creatorSummary);
        response.setCreationDateTime(request.getCreatedAt().toEpochMilli());
        response.setApprover(approverSummary);

        return response;
    }

    @Override
    public Long sumOfRequest(Long id) {
        return requestRepository.countByCreatedBy(id);
    }

    @Override
    public void delete(Long id) {
       requestRepository.deleteById(id);
    }

    @Override
    public Map<String, ObjectResponse> getAllItemRequests(PageAndSort model) {
        Page result = requestRepository.findAll(PagingAndSorting.paging(model));
        List<ItemRequest> ir = result.getContent();
        List<ItemRequestResponse> response = new ArrayList<>();


        ir.forEach(item->{
            User creator = userRepository.findById(item.getCreatedBy()).orElseThrow(() -> new ResourceNotFoundException("User","id", item.getCreatedBy()));
            UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getFirstName(), creator.getLastName());

            User approver = userRepository.findById(item.getApprover().getId()).orElseThrow(() -> new ResourceNotFoundException("User","id", item.getCreatedBy()));
            UserSummary approverSummary = new UserSummary(approver.getId(), approver.getUsername(), approver.getFirstName(), approver.getLastName());

            Image image = imageRepository.findById(item.getImage().getId()).orElseThrow(() -> new ResourceNotFoundException("Image","id", item.getImage().getId()));
            ImageSummary imageSummary = new ImageSummary(image.getId(), image.getObjectName(), image.getBucketName(), image.getContentType());

            ItemRequestResponse data = new ItemRequestResponse();
            data.setId(item.getId());
            data.setName(item.getName());
            data.setQuantity(item.getQuantity());
            data.setDetails(item.getDetails());
            data.setDocument(imageSummary);
            data.setCreatedBy(creatorSummary);
            data.setApprover(approverSummary);
            data.setCreationDateTime(item.getCreatedAt().toEpochMilli());
            response.add(data);
        });

        Map<String, ObjectResponse> responseMap = new HashedMap<>();
        ObjectResponse o = new ObjectResponse(response, result.getTotalPages(), result.isLast(), result.getTotalElements(), result.getSize(), result.getNumberOfElements(), result.isFirst(), result.isEmpty());
        responseMap.put("payload", o);

        return responseMap;
    }
}
