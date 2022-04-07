package com.example.annotationtest.entityRepository;

import com.example.annotationtest.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    void deleteById(Long id);

//    @Modifying
//    @Query("update Student s set s.age = :student where Student.id = :id")
//    Student updateStudent(@Param(value = "id") long id);

}
