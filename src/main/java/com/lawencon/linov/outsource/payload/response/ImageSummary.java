package com.lawencon.linov.outsource.payload.response;

public class ImageSummary {
    private Long id;
    private String objectName;
    private String bucketName;
    private String contentType;

    public ImageSummary(Long id, String objectName, String bucketName, String contentType) {
        this.id = id;
        this.objectName = objectName;
        this.bucketName = bucketName;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
