package com.student.dto;

public class StudentDTO {

    private Long id;
    private String studentFirstName;
    private String studentLastName;
    private String email;
    private String password;


    public StudentDTO() {
    }

    public StudentDTO(Long id, String studentFirstName, String studentLastName, String email, String password) {
        this.id = id;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentFirstName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", studentName='" + studentFirstName + '\'' +
                ", studentName='" + studentLastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
