package com.lawencon.linov.outsource.model.claim;

import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.audit.UserDateAudit;
import com.lawencon.linov.outsource.model.authentication.User;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approver", referencedColumnName = "user_id")
    private User approver;

    @Column(name = "status")
    private StatusName status;

    public OverTime() {
    }

    public OverTime(Time startTime, Time endTime, String reason, Image image, User approver, StatusName status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.image = image;
        this.approver = approver;
        this.status = status;
    }

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

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public StatusName getStatus() {
        return status;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }
}