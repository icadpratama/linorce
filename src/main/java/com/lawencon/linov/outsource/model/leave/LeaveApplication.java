package com.lawencon.linov.outsource.model.leave;

import com.lawencon.linov.outsource.model.audit.UserDateAudit;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "leave_applications")
public class LeaveApplication extends UserDateAudit {
}
