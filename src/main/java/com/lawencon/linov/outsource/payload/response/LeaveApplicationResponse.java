package com.lawencon.linov.outsource.payload.response;

import com.lawencon.linov.outsource.util.DayType;
import com.lawencon.linov.outsource.util.LeaveType;
import com.lawencon.linov.outsource.util.StatusName;

import java.sql.Timestamp;

public class LeaveApplicationResponse {

    private Long id;
    private Timestamp start;
    private DayType startDayType;
    private DayType endDayType;
    private Timestamp end;
    private String reason;
    private ImageSummary document;
    private UserSummary approver;
    private StatusName status;
    private LeaveType leaveType;
    private UserSummary createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public DayType getStartDayType() {
        return startDayType;
    }

    public void setStartDayType(DayType startDayType) {
        this.startDayType = startDayType;
    }

    public DayType getEndDayType() {
        return endDayType;
    }

    public void setEndDayType(DayType endDayType) {
        this.endDayType = endDayType;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ImageSummary getDocument() {
        return document;
    }

    public void setDocument(ImageSummary document) {
        this.document = document;
    }

    public UserSummary getApprover() {
        return approver;
    }

    public void setApprover(UserSummary approver) {
        this.approver = approver;
    }

    public StatusName getStatus() {
        return status;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public UserSummary getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserSummary createdBy) {
        this.createdBy = createdBy;
    }
}
