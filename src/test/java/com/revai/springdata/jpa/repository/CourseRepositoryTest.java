package com.revai.springdata.jpa.repository;

import com.revai.springdata.jpa.entity.Course;
import com.revai.springdata.jpa.entity.Student;
import com.revai.springdata.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Ken")
                .lastName("Thompson")
                .build();
        Course course = Course.builder()
                .title("Go")
                .credit(6)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getContent();

        long totalElements = courseRepository.findAll(firstPageWithThreeRecords)
                        .getTotalElements();
        long totalPages = courseRepository.findAll(firstPageWithThreeRecords)
                .getTotalPages();

        System.out.println("Total Pages : " + totalPages);
        System.out.println("Total Elements : " + totalElements);

        courses.stream().forEach(System.out::println);

        long totalPages2 = courseRepository.findAll(firstPageWithThreeRecords)
                .getTotalPages();

        System.out.println("Total Pages : " + totalPages2);

        List<Course> courses2 =
                courseRepository.findAll(secondPageWithTwoRecords)
                        .getContent();
        courses2.stream().forEach(System.out::println);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(
                0, 2, Sort.by("title")
        );

        Pageable sortByCreditDesc = PageRequest.of(
                0, 3, Sort.by("credit").descending()
        );

        Pageable sortByTitleAndCreditDesc = PageRequest.of(
                0, 3,
                Sort.by("title").descending()
                        .and(Sort.by("credit"))
        );

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        courses.stream().forEach(System.out::println);
    }

    @Test
    public void printFindByTitleContaining() {
        Pageable firstPageWithTenRecords =
                PageRequest.of(0, 10);

        List<Course> courses = courseRepository
                .findByTitleContaining("S", firstPageWithTenRecords)
                .getContent();
        courses.stream().forEach(System.out::println);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Arnold")
                .lastName("Sharma")
                .build();
        Student student = Student.builder()
                .firstName("Abhi")
                .lastName("Sharma")
                .emailId("abhi777@gmail.com")
                .build();
        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();
        course.addStudents(student);

        courseRepository.save(course);

    }

}