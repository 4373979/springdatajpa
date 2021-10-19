package com.lwm.springdatajpa.controller;

import com.lwm.springdatajpa.entity.User;
import com.lwm.springdatajpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping()
    public String saveUser(@RequestBody User user) {
        User user0;
        try {
            user0 = userService.save(user);
            System.out.println(user0.toString());
            if (user0.getId()!=null){
                LOGGER.info("/ register....................."+user0.toString());
                return "success";
            }else {
                return "fail";
            }
        }catch (Exception e){
            LOGGER.info("/ register....................."+e.getStackTrace());
            return "fail";
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        userService.deleteById(userId);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") String userId, @RequestBody User user) {
        user.setId(userId);
        return userService.saveAndFlush(user);
    }

    @GetMapping("/{id}")
    public User getUserInfo(@PathVariable("id") String userId) {
        Optional<User> optional = userService.findById(userId);
        return optional.orElseGet(User::new);
    }

    @PostMapping("/select")
    public String selUser(@RequestBody User user) {
        List<User> users;
        try {
            users = userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
            User user0 = users.get(0);
            System.out.println(users.toString());
            if (user0.getId()!=null){
                LOGGER.info("/select Login....................."+user0.toString());
                return "success";
            }else {
                return "fail";
            }
        }catch (Exception e){
            LOGGER.info("/select Login....................."+e.getStackTrace());
            return "fail";
        }
    }

    @GetMapping("/list")
    public Page<User> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
//        return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
        Sort.Order orders = null;
        Pageable pageable = null;
        orders=new Sort.Order(Sort.Direction.DESC, "id");
        pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(orders));
        return userService.findAll(pageable);
    }

}