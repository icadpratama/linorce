package com.lawencon.linov.outsource.model.authentication;

import com.lawencon.linov.outsource.model.audit.DateAudit;
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
}
