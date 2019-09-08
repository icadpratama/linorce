package com.lawencon.linov.outsource.repository;

import com.lawencon.linov.outsource.model.approval.ItemRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRequestRepository extends PagingAndSortingRepository<ItemRequest, Long> {

    long countByCreatedBy(Long userId);
}
