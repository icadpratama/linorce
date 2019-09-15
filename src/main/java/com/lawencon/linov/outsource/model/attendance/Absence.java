package com.lawencon.linov.outsource.model.attendance;

import com.lawencon.linov.outsource.model.audit.UserDateAudit;
import com.lawencon.linov.outsource.util.AbsenceType;
import org.hibernate.annotations.NaturalId;

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

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private AbsenceType type;

    public Absence(String location, String projectName, AbsenceType type) {
        this.location = location;
        this.projectName = projectName;
        this.type = type;
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

    public AbsenceType getType() {
        return type;
    }

    public void setType(AbsenceType type) {
        this.type = type;
    }
}
