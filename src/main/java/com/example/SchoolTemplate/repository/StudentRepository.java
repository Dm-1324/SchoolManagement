package com.example.SchoolTemplate.repository;

import com.example.SchoolTemplate.entity.Student;
import com.example.SchoolTemplate.enums.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findBySchoolId(Long schoolId);

    List<Student> findByGrade(Grade grade);

    List<Student> findByMarksGreaterThan(Long marks);

    @Query("select s from Student s where marks > :lower and marks < :higher")
    List<Student> findMarksInRange(@Param("lower") Long lower, @Param("higher") Long higher);

    
}
