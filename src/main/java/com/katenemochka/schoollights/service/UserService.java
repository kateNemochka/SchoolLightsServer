package com.katenemochka.schoollights.service;

import com.katenemochka.schoollights.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAll();

    User getUserById(Long id);

    User getUserByEmail(String email);

    User createOrUpdateUser(User user);

    void deleteUserById(Long id);

    List<User> getAllByRole(String role);

    User findUserByEmailAndPassword(String email, String password);
}
