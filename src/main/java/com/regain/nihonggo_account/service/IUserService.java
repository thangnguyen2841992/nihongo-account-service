package com.regain.nihonggo_account.service;

import com.regain.nihonggo_account.model.dto.UserDTO;

import java.util.List;

public interface IUserService {
    String  register(UserDTO userDTO);

    String activeAccount(String username);

    List<UserDTO> getUsers();
}
