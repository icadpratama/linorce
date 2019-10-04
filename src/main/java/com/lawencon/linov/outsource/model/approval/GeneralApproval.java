package com.lawencon.linov.outsource.model.approval;

import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.audit.UserDateAudit;
import com.lawencon.linov.outsource.model.authentication.User;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approver", referencedColumnName = "user_id")
    private User approver;

    @Column(name = "status")
    private StatusName status;

    public GeneralApproval() {
    }

    public GeneralApproval(String subject, String requestDetails, Image image, User approver, StatusName status) {
        this.subject = subject;
        this.requestDetails = requestDetails;
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
