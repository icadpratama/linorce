package com.lawencon.linov.outsource.model.attendance;

import com.lawencon.linov.outsource.model.audit.UserDateAudit;

import javax.persistence.*;

@Entity
@Table(name = "absences")
public class Absence extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "location")
    private String location;

    @Column(name = "project_name")
    private String projectName;

    public Absence(String location, String projectName) {
        this.location = location;
        this.projectName = projectName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
