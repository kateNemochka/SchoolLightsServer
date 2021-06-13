package com.katenemochka.schoollights.dao;

import com.katenemochka.schoollights.domain.Role;
import com.katenemochka.schoollights.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAllByRole(Role role);
}
