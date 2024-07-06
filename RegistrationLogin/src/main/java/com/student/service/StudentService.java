package com.student.service;

import com.student.dto.LoginDTO;
import com.student.dto.StudentDTO;
import com.student.response.LoginResponse;

public interface StudentService {

    String addStudent(StudentDTO studentDTO);
    LoginResponse loginStudent(LoginDTO loginDTO);
}
