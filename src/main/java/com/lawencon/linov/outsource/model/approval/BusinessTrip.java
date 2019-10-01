package com.lawencon.linov.outsource.model.approval;

import com.lawencon.linov.outsource.model.audit.UserDateAudit;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "business_trips")
public class BusinessTrip extends UserDateAudit {
}
