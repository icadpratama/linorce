package com.lawencon.linov.outsource.model.approval;

import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.audit.UserDateAudit;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.util.StatusName;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "item_requests")
public class ItemRequest extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @NotBlank(message = "item can't be blank")
    @Size(min=3, max = 50, message = "name min length is 3 and max length is 50")
    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @NotBlank(message = "details can't be blank")
    @Size(min = 10, max = 250, message = "details min length is 10 and max length is 250")
    @Column(name = "details")
    private String details;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approver", referencedColumnName = "user_id")
    private User approver;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private StatusName status;

    public ItemRequest() {
    }

    public ItemRequest(@NotBlank(message = "item can't be blank") @Size(min = 3, max = 50, message = "name min length is 3 and max length is 50") String name, Integer quantity, @NotBlank(message = "details can't be blank") @Size(min = 10, max = 250, message = "details min length is 10 and max length is 250") String details, Image image, User approver, StatusName status) {
        this.name = name;
        this.quantity = quantity;
        this.details = details;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
