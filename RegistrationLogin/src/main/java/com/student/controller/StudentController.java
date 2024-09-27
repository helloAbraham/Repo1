package com.student.controller;

import com.student.dto.LoginDTO;
import com.student.dto.StudentDTO;
import com.student.response.LoginResponse;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/save")
    public String saveStudent(@RequestBody StudentDTO studentDTO){
        String id = studentService.addStudent(studentDTO);
        return "Hello, '" + id + "', your information was successfully recorded.";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginStudent(@RequestBody LoginDTO loginDTO){
        LoginResponse loginResponse = studentService.loginStudent(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/logout")
    public String logoutPage() {
        // Perform additional logout tasks if needed
        return "redirect:/login?logout"; // Redirect to login page with a logout parameter
    }
    @GetMapping("/dashboardCheck")
    public String welcomeBoardCheck() {
        return "Welcome! This is your dashboard. Without login";
    }
    @GetMapping("/dashboard")
    public String welcomeBoard() {
        return "Welcome! This is your dashboard.";
    }

}
