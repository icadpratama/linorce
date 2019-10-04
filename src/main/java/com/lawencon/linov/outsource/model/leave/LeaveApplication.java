package com.lawencon.linov.outsource.model.leave;

import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.audit.UserDateAudit;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.util.DayType;
import com.lawencon.linov.outsource.util.LeaveType;
import com.lawencon.linov.outsource.util.StatusName;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "leave_applications")
public class LeaveApplication extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start")
    private Timestamp start;

    @Column(name = "start_day_type")
    private DayType startDayType;

    @Column(name = "end_day_type")
    private DayType endDayType;

    @Column(name = "ends")
    private Timestamp end;

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

    @Column(name = "leave_type")
    private LeaveType leaveType;

    public LeaveApplication() {
    }

    public LeaveApplication(Timestamp start, DayType startDayType, DayType endDayType, Timestamp end, String reason, Image image, User approver, StatusName status, LeaveType leaveType) {
        this.start = start;
        this.startDayType = startDayType;
        this.endDayType = endDayType;
        this.end = end;
        this.reason = reason;
        this.image = image;
        this.approver = approver;
        this.status = status;
        this.leaveType = leaveType;
    }

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

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }
}
