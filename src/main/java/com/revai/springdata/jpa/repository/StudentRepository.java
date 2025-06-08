package com.revai.springdata.jpa.repository;

import com.revai.springdata.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameContaining(String name);

    public List<Student> findByLastNameIsNotNull();

    public List<Student> findByGuardianName(String guardianName);

    public Student findByFirstNameAndLastName(String firstName, String lastName);

    // JPQL queries are created based on the Java Entity classes not on database tables.
    @Query(value = "Select s from Student s where s.emailId = ?1")
    public Student getStudentByEmailAddress(String emailId);

    @Query(value = "Select s.firstName from Student s where s.emailId = ?1")
    public String getStudentFirstNameByEmailAddress(String emailId);

    // Native SQL query
    @Query(
            value = "Select * from tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressNative(String emailId);

    @Query(
            value = "Select s from Student s where s.lastName = :lastName " +
                    "and s.guardian.name = :guardianName"
    )
    public Student getStudentByLastNameAndGuardianNameNamedParam(@Param("lastName") String lastName,
                                                       @Param("guardianName") String guardianName);

    @Query(
            value = "Select * from tbl_student s where s.last_name = :lastName " +
                    "and s.guardian_name = :guardianName",
            nativeQuery = true
    )
    public Student getStudentByLastNameAndGuardianNameNative(@Param("lastName") String lastName,
                                                       @Param("guardianName") String guardianName);

    @Transactional
    @Modifying
    @Query(
            value = "update tbl_student set last_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    public int updateStudentNameByEmailId(String lastName, String emailId);

}
