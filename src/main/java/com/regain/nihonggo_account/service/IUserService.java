package com.regain.nihonggo_account.service;

import com.regain.nihonggo_account.model.dto.UserDTO;

public interface IUserService {
    String  register(UserDTO userDTO);

    String activeAccount(String username);
}
