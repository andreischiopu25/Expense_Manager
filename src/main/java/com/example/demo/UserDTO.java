package com.example.demo;

import jakarta.validation.constraints.*;

public class UserDTO {

    @NotBlank(message = "User name should not be null")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password should not be null")
    @Size(min = 5, message = "Password should be minimum {min} characters")
    private String password;

    private String confirmpassword;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String password, String confirmpassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                '}';
    }
}
