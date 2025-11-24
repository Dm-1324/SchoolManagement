package com.example.SchoolTemplate.service;

import com.example.SchoolTemplate.dto.schoolDto.SchoolAverageView;
import com.example.SchoolTemplate.dto.schoolDto.SchoolDto;
import com.example.SchoolTemplate.dto.schoolDto.SchoolInputDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolService {

    List<SchoolDto> getSchoolList();

    SchoolDto getSchoolById(Long schoolId);

    SchoolInputDto createSchool(SchoolInputDto schoolDto);

    SchoolDto updateSchoolInfo(Long id, SchoolInputDto schoolDto);

    void deleteSchool(Long id);

    Long countStudentBySchoolId(Long schoolId);

    List<SchoolAverageView> getAverageBySchool();
}
