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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService{

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
                    Optional<Role> roleOptional = this.roleRepository.findByNameContaining(userDTO.getRole()[i]);
                    roleOptional.ifPresent(role -> roles.add(new Role(role.getRoleId(), role.getName())));
                }
                user.setRoles(roles);
            }
            return "User registered successfully";
    }
}
