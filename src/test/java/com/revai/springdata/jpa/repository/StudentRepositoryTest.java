package com.revai.springdata.jpa.repository;

import com.revai.springdata.jpa.entity.Guardian;
import com.revai.springdata.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Guardian guardian = Guardian.builder()
                .name("Sanjay")
                .email("sanju@gmail.com")
                .mobile("123456")
                .build();
        Student student = Student.builder()
                .emailId("dileep@gmail.com")
                .firstName("Pentakota")
                .lastName("Sai Dileep")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudents() {
        List<Student> students = studentRepository.findAll();
        students.stream().forEach(System.out::println);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> studentList = studentRepository.findByFirstName("Pentakota");
        studentList.stream().forEach(System.out::println);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> list = studentRepository.findByFirstNameContaining("ent");
        list.stream().forEach(System.out::println);
    }

    @Test
    public void printStudentsFindByLastNameIsNotNull() {
        List<Student> lastNameIsNotNull = studentRepository.findByLastNameIsNotNull();
        lastNameIsNotNull.stream()
                .map(Student::getLastName)
                .forEach(System.out::println);
    }

    @Test
    public void printStudentBasedOnGuardianName() {
        List<Student> guardian = studentRepository.findByGuardianName("Sanjay");
        guardian.stream()
                .map(Student::getGuardian)
                .map(Guardian::getName)
                .forEach(System.out::println);
    }

    @Test
    public void printStudentsByFirstNameAndLastName() {
        Student student = studentRepository.findByFirstNameAndLastName("Pentakota", "Sairevanth");
        System.out.println(student);
    }

    @Test
    public void printStudentByEmail() {
        Student student = studentRepository.getStudentByEmailAddress("sairev@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printStudentFirstNameByEmailAddress() {
        String studentFirstName = studentRepository
                .getStudentFirstNameByEmailAddress("sairev@gmail.com");
        System.out.println(studentFirstName);
    }

    @Test
    public void printStudentByEmailAddressNative() {
        Student student = studentRepository
                .getStudentByEmailAddressNative("sairev@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printStudentByLastNameAndGuardianName() {
        Student student = studentRepository
                .getStudentByLastNameAndGuardianNameNamedParam("Sairevanth", "Sanjay");
        System.out.println(student);
    }

    @Test
    public void printStudentByLastNameAndGuardianNameNative() {
        Student student = studentRepository
                .getStudentByLastNameAndGuardianNameNative("Sairevanth", "Sanjay");
        System.out.println(student);
    }

    @Test
    public void updateStudentNameByEmailId() {
        int count = studentRepository.updateStudentNameByEmailId("Sai Revanth", "sairev@gmail.com");
        System.out.println(count + " rows updated!!!");
    }
}