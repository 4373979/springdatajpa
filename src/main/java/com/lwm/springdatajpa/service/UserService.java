package com.lwm.springdatajpa.service;

import com.lwm.springdatajpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User save(User user);

    void deleteById(String userId);

    User saveAndFlush(User user);

    Optional<User> findById(String userId);

    List<User> findByUsernameAndPassword(String username,String password);

    Page<User> findAll(Pageable pageable);

}
