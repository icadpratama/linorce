package com.lawencon.linov.outsource.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lawencon.linov.outsource.model.approval.BusinessTrip;
import com.lawencon.linov.outsource.model.approval.GeneralApproval;
import com.lawencon.linov.outsource.model.approval.ItemRequest;
import com.lawencon.linov.outsource.model.approval.SupportFromHome;
import com.lawencon.linov.outsource.model.audit.DateAudit;
import com.lawencon.linov.outsource.model.claim.FinancialClaim;
import com.lawencon.linov.outsource.model.claim.OverTime;
import com.lawencon.linov.outsource.model.leave.LeaveApplication;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "first name can't be blank")
    @Size(max = 20, message = "first name max length is 20")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "last name can't be blank")
    @Size(max = 20, message = "last name max length is 20")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Size(max = 15)
    @Column(name = "username")
    private String username;

    @NaturalId
    @NotBlank(message = "email can't be blank")
    @Size(max = 100, message = "email max length is 100")
    @Email(message = "email is not valid")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "password can't be blank")
    @Size(max = 100, message = "password max length is 100")
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "approver")
    @JsonIgnore
    private Set<ItemRequest> itemRequest = new HashSet<>();

    @OneToMany(mappedBy = "approver")
    @JsonIgnore
    private Set<BusinessTrip> businessTrip = new HashSet<>();

    @OneToMany(mappedBy = "approver")
    @JsonIgnore
    private Set<GeneralApproval> generalApproval = new HashSet<>();

    @OneToMany(mappedBy = "approver")
    @JsonIgnore
    private Set<SupportFromHome> supportFromHome = new HashSet<>();

    @OneToMany(mappedBy = "approver")
    @JsonIgnore
    private Set<FinancialClaim> financialClaim = new HashSet<>();

    @OneToMany(mappedBy = "approver")
    @JsonIgnore
    private Set<OverTime> overTime = new HashSet<>();

    @OneToMany(mappedBy = "approver")
    @JsonIgnore
    private Set<LeaveApplication> leaveApplication = new HashSet<>();

    public User() {
    }

    public User(@NotBlank(message = "first name can't be blank") @Size(max = 20, message = "first name max length is 20") String firstName, @NotBlank(message = "last name can't be blank") @Size(max = 20, message = "last name max length is 20") String lastName, @NotBlank @Size(max = 15) String username, @NotBlank(message = "email can't be blank") @Size(max = 100, message = "email max length is 100") @Email(message = "email is not valid") String email, @NotBlank(message = "password can't be blank") @Size(max = 100, message = "password max length is 100") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<ItemRequest> getItemRequest() {
        return itemRequest;
    }

    public void setItemRequest(Set<ItemRequest> itemRequest) {
        this.itemRequest = itemRequest;
    }

    public Set<BusinessTrip> getBusinessTrip() {
        return businessTrip;
    }

    public void setBusinessTrip(Set<BusinessTrip> businessTrip) {
        this.businessTrip = businessTrip;
    }

    public Set<GeneralApproval> getGeneralApproval() {
        return generalApproval;
    }

    public void setGeneralApproval(Set<GeneralApproval> generalApproval) {
        this.generalApproval = generalApproval;
    }

    public Set<SupportFromHome> getSupportFromHome() {
        return supportFromHome;
    }

    public void setSupportFromHome(Set<SupportFromHome> supportFromHome) {
        this.supportFromHome = supportFromHome;
    }

    public Set<FinancialClaim> getFinancialClaim() {
        return financialClaim;
    }

    public void setFinancialClaim(Set<FinancialClaim> financialClaim) {
        this.financialClaim = financialClaim;
    }

    public Set<OverTime> getOverTime() {
        return overTime;
    }

    public void setOverTime(Set<OverTime> overTime) {
        this.overTime = overTime;
    }

    public Set<LeaveApplication> getLeaveApplication() {
        return leaveApplication;
    }

    public void setLeaveApplication(Set<LeaveApplication> leaveApplication) {
        this.leaveApplication = leaveApplication;
    }
}
