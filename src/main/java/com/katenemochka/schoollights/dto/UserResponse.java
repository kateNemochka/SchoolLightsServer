package com.katenemochka.schoollights.dto;

import com.katenemochka.schoollights.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserResponse {
    private String first_name;
    private String last_name;
    private String email;
    private String role;

    public static List<UserResponse> convertToUserResponse(List<User> users) {
        return users.stream()
                .map(user -> new UserResponse(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole().getName()))
                .collect(Collectors.toList());
    }
}