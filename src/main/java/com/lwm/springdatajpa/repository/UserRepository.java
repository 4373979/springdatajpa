package com.lwm.springdatajpa.repository;

import com.lwm.springdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUsernameAndPassword(String username,String password);
}

