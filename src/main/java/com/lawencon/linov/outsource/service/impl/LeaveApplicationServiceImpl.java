package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.exception.ResourceNotFoundException;
import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.model.leave.LeaveApplication;
import com.lawencon.linov.outsource.payload.response.ImageSummary;
import com.lawencon.linov.outsource.payload.response.LeaveApplicationResponse;
import com.lawencon.linov.outsource.payload.response.ObjectResponse;
import com.lawencon.linov.outsource.payload.response.UserSummary;
import com.lawencon.linov.outsource.repository.ImageRepository;
import com.lawencon.linov.outsource.repository.LeaveApplicationRepository;
import com.lawencon.linov.outsource.repository.UserRepository;
import com.lawencon.linov.outsource.service.LeaveApplicationService;
import com.lawencon.linov.outsource.util.*;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

    private final LeaveApplicationRepository repository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public LeaveApplicationServiceImpl(LeaveApplicationRepository repository, UserRepository userRepository, ImageRepository imageRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public Map<String, ObjectResponse> getAllLeaveApplications(PageAndSort model) {
        Page result = repository.findAll(PagingAndSorting.paging(model));
        List<LeaveApplication> la = result.getContent();
        List<LeaveApplicationResponse> response = new ArrayList<>();

        la.forEach(item->{
            User creator = userRepository.findById(item.getCreatedBy()).orElseThrow(() -> new ResourceNotFoundException("User","id", item.getCreatedBy()));
            UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getFirstName(), creator.getLastName());

            User approver = userRepository.findById(item.getApprover().getId()).orElseThrow(() -> new ResourceNotFoundException("User","id", item.getCreatedBy()));
            UserSummary approverSummary = new UserSummary(approver.getId(), approver.getUsername(), approver.getFirstName(), approver.getLastName());

            Image image = imageRepository.findById(item.getImage().getId()).orElseThrow(() -> new ResourceNotFoundException("Image","id", item.getImage().getId()));
            ImageSummary imageSummary = new ImageSummary(image.getId(), image.getObjectName(), image.getBucketName(), image.getContentType());

            LeaveApplicationResponse data = new LeaveApplicationResponse();
            data.setId(item.getId());
            data.setStart(item.getStart());
            data.setStartDayType(item.getStartDayType());
            data.setEnd(item.getEnd());
            data.setEndDayType(item.getEndDayType());
            data.setReason(item.getReason());
            data.setDocument(imageSummary);
            data.setApprover(approverSummary);
            data.setStatus(item.getStatus());
            data.setLeaveType(item.getLeaveType());
            data.setCreatedBy(creatorSummary);
            response.add(data);
        });

        Map<String, ObjectResponse> responseMap = new HashedMap<>();
        ObjectResponse o = new ObjectResponse(response, result.getTotalPages(), result.isLast(), result.getTotalElements(), result.getSize(), result.getNumberOfElements(), result.isFirst(), result.isEmpty());
        responseMap.put("payload", o);

        return responseMap;
    }
}
