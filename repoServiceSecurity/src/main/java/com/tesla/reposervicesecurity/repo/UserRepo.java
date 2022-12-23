package com.tesla.reposervicesecurity.repo;

import com.tesla.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByToken(Integer token);
}

