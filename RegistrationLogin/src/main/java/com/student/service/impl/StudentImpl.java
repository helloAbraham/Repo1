package com.student.service.impl;

import com.student.dto.LoginDTO;
import com.student.dto.StudentDTO;
import com.student.entity.StudentInfo;
import com.student.repository.StudentRepository;
import com.student.response.LoginResponse;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class StudentImpl implements StudentService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepository studentRepo;
    @Override
    public String addStudent(StudentDTO studentDTO) {
        StudentInfo studentObject = new StudentInfo(
                studentDTO.getId(),
                studentDTO.getStudentFirstName(),
                studentDTO.getStudentLastName(),
                studentDTO.getPhoneNumber(),
                studentDTO.getEmail(),
                this.passwordEncoder.encode(studentDTO.getPassword())
                );

        studentRepo.save(studentObject);
        return studentObject.getStudentFirstName() + " " + studentObject.getStudentLastName();
    }
    @Override
    public LoginResponse loginStudent(LoginDTO loginDTO) {
        String msg = "";
        StudentInfo stu = studentRepo.findByEmail(loginDTO.getEmail());
        //All the conditions below
        if(stu != null){
            String password = loginDTO.getPassword();
            String encodedPassword = stu.getPassword();
            Boolean isPwdCorrect = passwordEncoder.matches(password, encodedPassword);
            if(isPwdCorrect) {
                Optional<StudentInfo> student = studentRepo.findOneByEmailAndPassword(loginDTO.getEmail(),encodedPassword );
                if(student.isPresent()) {
                    return new LoginResponse("Welcome!, " + stu.getStudentFirstName() + " " + stu.getStudentLastName(), true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("Password Not Match", false);
            }
        } else {
            return new LoginResponse("Email does not exists", false);
        }

    }
}
