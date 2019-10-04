package com.lawencon.linov.outsource.payload.response;

public class ItemRequestResponse {
    private Long id;
    private String name;
    private Integer quantity;
    private String details;
    private ImageSummary document;
    private UserSummary createdBy;
    private UserSummary approver;
    private Long creationDateTime;
    private String status;

    public ItemRequestResponse() {
    }

    public ItemRequestResponse(Long id, String name, Integer quantity, String details, ImageSummary document, Long creationDateTime, UserSummary approver) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.details = details;
        this.document = document;
        this.creationDateTime = creationDateTime;
        this.approver = approver;
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

    public ImageSummary getDocument() {
        return document;
    }

    public void setDocument(ImageSummary document) {
        this.document = document;
    }

    public UserSummary getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserSummary createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Long creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public UserSummary getApprover() {
        return approver;
    }

    public void setApprover(UserSummary approver) {
        this.approver = approver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
