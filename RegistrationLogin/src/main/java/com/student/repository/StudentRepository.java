package com.student.repository;

import com.student.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<StudentInfo, Long> {

    Optional<StudentInfo> findOneByEmailAndPassword(String email, String password);

    StudentInfo findByEmail(String email);
}
