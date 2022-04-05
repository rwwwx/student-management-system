package com.example.annotationtest.entityRepository;

import com.example.annotationtest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    @Modifying
    @Query("update Student s set s.email = :email where s.id = :id")
    Student updateStudentEmail(@Param(value = "id") long id, @Param(value = "email") String newEmail);

}
