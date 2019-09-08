package com.lawencon.linov.outsource.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @NotBlank(message = "first name can't be blank")
    @Size(min = 4, max = 20, message = "first name min length is 4 and max length is 20")
    private String firstName;

    @NotBlank(message = "last name can't be blank")
    @Size(min = 4, max = 20, message = "last name min length is 4 and max length is 20")
    private String lastName;

    @NotBlank(message = "username can't be blank")
    @Size(min = 3, max = 15, message = "username min length is 3 and max length is 15")
    private String username;

    @NotBlank(message = "email can't be blank")
    @Size(min = 6, max = 100, message = "email min length is 6 and max length is 100")
    @Email(message = "email is not valid")
    private String email;

    @NotBlank(message = "password can't be blank")
    @Size(min = 6, max = 20, message = "password min length is 6 and max length is 20")
    private String password;

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
}
