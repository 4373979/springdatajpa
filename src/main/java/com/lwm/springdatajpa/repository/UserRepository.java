package com.lwm.springdatajpa.repository;

import com.lwm.springdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
