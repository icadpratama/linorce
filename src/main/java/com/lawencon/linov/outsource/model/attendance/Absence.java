package com.lawencon.linov.outsource.model.attendance;

import com.lawencon.linov.outsource.model.audit.UserDateAudit;
import com.lawencon.linov.outsource.util.AbsenceType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @Column(name = "start")
    @CreationTimestamp
    private Timestamp start;

    @Column(name = "ends")
    @UpdateTimestamp
    private Timestamp end;

    @Column(name = "activity")
    private String activity;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(length = 60, name = "absence_type")
    private AbsenceType type;

    public Absence() {
    }

    public Absence(String location, String projectName, String activity, Integer month, Integer year, AbsenceType type) {
        this.location = location;
        this.projectName = projectName;
        this.activity = activity;
        this.month = month;
        this.year = year;
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

    public AbsenceType getType() {
        return type;
    }

    public void setType(AbsenceType type) {
        this.type = type;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
