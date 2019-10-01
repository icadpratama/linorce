package com.lawencon.linov.outsource.model.approval;

import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.audit.UserDateAudit;
import com.lawencon.linov.outsource.util.StatusName;

import javax.persistence.*;

@Entity
@Table(name = "general_approvals")
public class GeneralApproval extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "details")
    private String requestDetails;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRequestDetails() {
        return requestDetails;
    }

    public void setRequestDetails(String requestDetails) {
        this.requestDetails = requestDetails;
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
