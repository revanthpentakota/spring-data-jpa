package com.revai.springdata.jpa.repository;

import com.revai.springdata.jpa.entity.Course;
import com.revai.springdata.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .build();

        Course courseDSA = Course.builder()
                .title("DSA")
                .credit(6)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Abdul")
                .lastName("Kalam")
               // .courses(List.of(courseDBA, courseDSA))
                .build();

        teacherRepository.save(teacher);
    }
}