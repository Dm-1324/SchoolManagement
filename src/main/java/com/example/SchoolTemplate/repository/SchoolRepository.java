package com.example.SchoolTemplate.repository;

import com.example.SchoolTemplate.dto.schoolDto.SchoolAverageView;
import com.example.SchoolTemplate.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("select count(s) from Student s where s.school.id = :schoolId")
    Long countStudentsBySchoolId(@Param("schoolId") Long schoolId);

    @Query("""
            SELECT 
                s.id AS schoolId,
                s.schoolName AS schoolName,
                AVG(st.marks) AS avgMarks
            FROM School s
            JOIN s.students st
            GROUP BY s.id, s.schoolName
            """)
    List<SchoolAverageView> getAverageMark();
}
