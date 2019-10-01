package com.lawencon.linov.outsource.payload.request;

import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.util.StatusName;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ItemReqRequest {

    @NotBlank(message = "item can't be blank")
    @Size(min=3, max = 50, message = "name min length is 3 and max length is 50")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @NotBlank(message = "details can't be blank")
    @Size(min = 10, max = 250, message = "details min length is 10 and max length is 250")
    private String details;

    private Image image;

    @NotBlank(message = "choose one approval")
    private Long userId;

    private StatusName status;

    public ItemReqRequest(@NotBlank(message = "item can't be blank") @Size(min = 3, max = 50, message = "name min length is 3 and max length is 50") String name, Integer quantity, @NotBlank(message = "details can't be blank") @Size(min = 10, max = 250, message = "details min length is 10 and max length is 250") String details, Image image, @NotBlank(message = "choose one approval") Long userId) {
        this.name = name;
        this.quantity = quantity;
        this.details = details;
        this.image = image;
        this.userId = userId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public StatusName getStatus() {
        return status;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }
}
