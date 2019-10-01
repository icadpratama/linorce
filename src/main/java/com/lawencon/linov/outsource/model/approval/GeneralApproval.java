package com.lawencon.linov.outsource.model.approval;

import com.lawencon.linov.outsource.model.audit.UserDateAudit;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "general_approvals")
public class GeneralApproval extends UserDateAudit {
}
