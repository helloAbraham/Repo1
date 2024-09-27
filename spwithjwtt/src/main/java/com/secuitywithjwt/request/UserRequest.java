package com.secuitywithjwt.request;

import com.secuitywithjwt.enums.Gender;
import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private Integer age;
    private String address;
    private String mobileNo;
    private String emailId;
    private String password;
    private Gender gender;
    private String roles;
}
