package com.bng.ddaja.users.repository;

import java.util.Optional;

import com.bng.ddaja.common.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    public User findById(long id);
    public Optional<User> findOptionalUserById(long id);
}