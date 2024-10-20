package com.challenge.operations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.challenge.operations.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
