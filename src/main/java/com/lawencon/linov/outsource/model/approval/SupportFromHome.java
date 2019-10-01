package com.lawencon.linov.outsource.model.approval;

import com.lawencon.linov.outsource.model.audit.UserDateAudit;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "support_homes")
public class SupportFromHome extends UserDateAudit {
}
