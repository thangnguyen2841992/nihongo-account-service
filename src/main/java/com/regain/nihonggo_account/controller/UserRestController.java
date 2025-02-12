package com.regain.nihonggo_account.controller;

import com.regain.nihonggo_account.model.dto.UserDTO;
import com.regain.nihonggo_account.model.entity.User;
import com.regain.nihonggo_account.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private IUserService userService;


    @PostMapping("/register")
    public String register(@RequestBody UserDTO user) {
        return userService.register(user);
    }

}
