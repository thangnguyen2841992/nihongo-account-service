package com.regain.nihonggo_account.controller;

import com.regain.nihonggo_account.client.INotificationService;
import com.regain.nihonggo_account.model.dto.MessageDTO;
import com.regain.nihonggo_account.model.dto.UserDTO;
import com.regain.nihonggo_account.model.entity.User;
import com.regain.nihonggo_account.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserRestController {
    @Autowired
    private IUserService userService;

    @Autowired
    private INotificationService notificationService;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO user) {
        String result =  userService.register(user);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("nguyenthang29tbdl@gmail.com");
        messageDTO.setTo(user.getEmail()); //username is Email
        messageDTO.setToName(user.getFirstName() + " " + user.getLastName());
        messageDTO.setSubject("Xác thực tài khoản");
        messageDTO.setContent("Chào mừng bạn đến với Website học tiếng Nhật");
        messageDTO.setUsername(user.getUsername());
        this.notificationService.sendNotificationEmail(messageDTO);
        return result;
    }

    @PutMapping("/active")
    public String active(@RequestParam(name = "username") String username) {
        return this.userService.activeAccount(username);
    }


}
