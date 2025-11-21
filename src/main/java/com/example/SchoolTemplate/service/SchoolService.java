package com.example.SchoolTemplate.service;

import com.example.SchoolTemplate.dto.SchoolDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolService {

    List<SchoolDto> getSchoolList();

    SchoolDto getSchoolById(Long schoolId);

    SchoolDto createSchool(SchoolDto schoolDto);

    SchoolDto updateSchoolInfo(Long id, SchoolDto schoolDto);

    void deleteSchool(Long id);
}
