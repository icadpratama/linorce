package com.lawencon.linov.outsource.model.claim;

import com.lawencon.linov.outsource.model.audit.UserDateAudit;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "financial_claims")
public class FinancialClaim extends UserDateAudit {
}
