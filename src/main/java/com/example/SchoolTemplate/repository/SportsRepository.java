package com.example.SchoolTemplate.repository;

import com.example.SchoolTemplate.entity.Sports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportsRepository extends JpaRepository<Sports, Long> {
    List<Sports> findBySchoolId(Long schoolId);
}
