package com.katenemochka.schoollights.dto;

import com.katenemochka.schoollights.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public User convertUserRequestToUser(){
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(password);
        return user;
    }
}
