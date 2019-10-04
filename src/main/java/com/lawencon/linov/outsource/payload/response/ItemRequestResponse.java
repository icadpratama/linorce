package com.lawencon.linov.outsource.payload.response;

import java.time.Instant;

public class ItemRequestResponse {
    private Long id;
    private String name;
    private Integer quantity;
    private String details;
    private String document;
    private UserSummary createdBy;
    private Instant creationDateTime;
    private String approver;

    public ItemRequestResponse() {
    }

    public ItemRequestResponse(Long id, String name, Integer quantity, String details, String document, Instant creationDateTime, String approver) {
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public UserSummary getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserSummary createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Instant creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }
}
