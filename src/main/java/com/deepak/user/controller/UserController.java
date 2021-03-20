package com.deepak.user.controller;

import com.deepak.user.VO.UserVO;
import com.deepak.user.entity.User;
import com.deepak.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User save(@RequestBody User user){
        log.info("Inside save method of UserController");
        return this.userService.save(user);
    }

    @GetMapping("/{id}")
    public UserVO getUserDepartment(@PathVariable("id") Long userId){
        log.info("Inside findById method of UserController");
        return this.userService.getUserDepartment(userId);
    }
}
