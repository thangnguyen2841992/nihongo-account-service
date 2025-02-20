package com.regain.nihonggo_account.service;

import com.regain.nihonggo_account.model.dto.UserDTO;
import com.regain.nihonggo_account.model.entity.Role;
import com.regain.nihonggo_account.model.entity.User;
import com.regain.nihonggo_account.repository.IRoleRepository;
import com.regain.nihonggo_account.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public String register(UserDTO userDTO) {
        if (!userDTO.getConfirmPassword().equals(userDTO.getPassword())) {
            return "Passwords do not match";
        } else {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getConfirmPassword()));
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setAddress(userDTO.getAddress());
            user.setFullName(userDTO.getFirstName() + " " + userDTO.getLastName());
            user.setGender(userDTO.getGender());
            user.setDateCreated(new Date());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date birthDate = formatter.parse(userDTO.getBirthday());
                user.setBirthday(birthDate);
            } catch (ParseException e) {
                return "Invalid birthday";
            }
            Set<Role> roles = new HashSet<>();
            for (int i = 0; i < userDTO.getRole().length; i++) {
                Optional<Role> roleOptional = this.roleRepository.findByName(userDTO.getRole()[i]);
                roleOptional.ifPresent(role -> roles.add(new Role(role.getRoleId(), role.getName())));
            }
            user.setRoles(roles);
            user.setAvatar("defaultAvatar.jpg");
            this.userRepository.save(user);
        }
        return "User registered successfully";
    }

    @Override
    public String activeAccount(String username) {
        Optional<User> userOptional = this.userRepository.findByUsernameContaining(username);
        if (userOptional.isEmpty()) {
            return "User not found";
        } else {
            User user = userOptional.get();
            if (user.isActive()) {
                return "User is already active";
            }
            user.setActive(true);
            this.userRepository.saveAndFlush(user);
        }
        return "Active Account successfully";
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setAvatar(user.getAvatar());
            userDTO.setFullName(user.getFullName());
            userDTO.setGender(user.getGender());
            userDTO.setBirthday(formatDateToString(user.getBirthday()));
            userDTO.setDateCreated(formatDateToString(user.getDateCreated()));
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhone());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    private String formatDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
