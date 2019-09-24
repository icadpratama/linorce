package com.lawencon.linov.outsource.payload.request;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

public class AbsenceRequest {

    @NotBlank(message = "location can't be blank")
    @Size(min=3, max = 250, message = "location min length is 3 and max length is 250")
    private String location;

    @NotBlank(message = "project name can't be blank")
    @Size(min=3, max = 50, message = "project name min length is 3 and max length is 50")
    private String projectName;

    @CreationTimestamp
    private Timestamp start;

    @UpdateTimestamp
    private Timestamp end;

    private String activity;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
