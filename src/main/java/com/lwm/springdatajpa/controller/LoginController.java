package com.lwm.springdatajpa.controller;

import com.lwm.springdatajpa.entity.User;
import com.lwm.springdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        userRepository.deleteById(userId);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") String userId, @RequestBody User user) {
        user.setId(userId);
        return userRepository.saveAndFlush(user);
    }

    @GetMapping("/{id}")
    public User getUserInfo(@PathVariable("id") String userId) {
        Optional<User> optional = userRepository.findById(userId);
        return optional.orElseGet(User::new);
    }

    @PostMapping("/select")
    public User selUser(@RequestBody User user) {
        List<User> users = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        return users.get(0);
    }

    @GetMapping("/list")
    public Page<User> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
//        return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
        Sort.Order orders = null;
        Pageable pageable = null;
        orders=new Sort.Order(Sort.Direction.DESC, "id");
        pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(orders));
        return userRepository.findAll(pageable);
    }

}