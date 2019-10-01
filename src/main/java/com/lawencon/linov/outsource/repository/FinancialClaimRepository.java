package com.lawencon.linov.outsource.repository;

import com.lawencon.linov.outsource.model.claim.FinancialClaim;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FinancialClaimRepository extends PagingAndSortingRepository<FinancialClaim, Long> {
}
