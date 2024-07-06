package com.student.controller;

import com.student.dto.LoginDTO;
import com.student.dto.StudentDTO;
import com.student.response.LoginResponse;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/save")
    public String saveStudent(@RequestBody StudentDTO studentDTO){
        String id = studentService.addStudent(studentDTO);
        return "Hello '" + id + "' Your information Successfully recorded.";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginStudent(@RequestBody LoginDTO loginDTO){
        LoginResponse loginResponse = studentService.loginStudent(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

}
