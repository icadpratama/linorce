package com.lawencon.linov.outsource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lawencon.linov.outsource.model.approval.ItemRequest;
import com.lawencon.linov.outsource.model.audit.UserDateAudit;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "bucket_name")
    private String bucketName;

    @Column(name = "size")
    private Long size;

    @Column(name = "content_type")
    private String contentType;

    @OneToOne(mappedBy = "image")
    @JsonIgnore
    private ItemRequest itemRequest;

    public Image() {
    }

    public Image(String objectName, String bucketName, Long size, String contentType) {
        this.objectName = objectName;
        this.bucketName = bucketName;
        this.size = size;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public ItemRequest getItemRequest() {
        return itemRequest;
    }

    public void setItemRequest(ItemRequest itemRequest) {
        this.itemRequest = itemRequest;
    }
}
