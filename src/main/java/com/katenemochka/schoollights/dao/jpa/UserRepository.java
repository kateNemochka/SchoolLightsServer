package com.katenemochka.schoollights.dao.jpa;

import com.katenemochka.schoollights.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
