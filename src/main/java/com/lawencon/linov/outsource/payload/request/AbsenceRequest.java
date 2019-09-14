package com.lawencon.linov.outsource.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AbsenceRequest {

    @NotBlank(message = "location can't be blank")
    @Size(min=3, max = 250, message = "location min length is 3 and max length is 250")
    private String location;

    @NotBlank(message = "project name can't be blank")
    @Size(min=3, max = 50, message = "project name min length is 3 and max length is 50")
    private String projectName;

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
}
