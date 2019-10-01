package com.lawencon.linov.outsource.model.claim;

import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.audit.UserDateAudit;
import com.lawencon.linov.outsource.util.StatusName;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "over_times")
public class OverTime extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "reason")
    private String reason;

    private Image image;

    @Column(name = "approver")
    private Long approver;

    @Column(name = "status")
    private StatusName status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Long getApprover() {
        return approver;
    }

    public void setApprover(Long approver) {
        this.approver = approver;
    }

    public StatusName getStatus() {
        return status;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }
}