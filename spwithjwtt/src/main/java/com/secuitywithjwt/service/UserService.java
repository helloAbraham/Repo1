package com.secuitywithjwt.service;

import com.secuitywithjwt.convertor.UserConvertor;
import com.secuitywithjwt.entities.User;
import com.secuitywithjwt.exceptions.UserExist;
import com.secuitywithjwt.repositories.UserRepository;
import com.secuitywithjwt.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserRequest userRequest) {

        //EmailId validation check
        Optional<User> users = userRepository.findByEmailId(userRequest.getEmailId());
        if(users.isPresent()) {
            throw new UserExist();
        }
        User usernew = UserConvertor.userDtoToUser(userRequest, passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(usernew);
        return "User information saved Successfully";
    }
}
