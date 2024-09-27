package com.secuitywithjwt.convertor;

import com.secuitywithjwt.entities.User;
import com.secuitywithjwt.request.UserRequest;
import com.secuitywithjwt.response.UserResponse;

public class UserConvertor {
    public static User userDtoToUser(UserRequest userRequest, String encodedPassword) {
        User user = User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .address(userRequest.getAddress())
                .gender(userRequest.getGender())
                .mobileNo(userRequest.getMobileNo())
                .emailId(userRequest.getEmailId())
                .password(userRequest.getPassword())
                .roles(userRequest.getRoles())
                .password(encodedPassword)
                .build();

        return user;
    }

    public static UserResponse userToUserDto(User user) {
        UserResponse userDto = UserResponse.builder()
                .name(user.getName())
                .age(user.getAge())
                .address(user.getAddress())
                .gender(user.getGender())
                .build();

        return userDto;
    }
}
