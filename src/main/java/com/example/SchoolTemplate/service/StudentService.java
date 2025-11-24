package com.example.SchoolTemplate.service;

import com.example.SchoolTemplate.dto.studentDto.StudentInputDto;
import com.example.SchoolTemplate.dto.studentDto.StudentMarksDto;
import com.example.SchoolTemplate.dto.studentDto.StudentOutputDto;
import com.example.SchoolTemplate.enums.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<StudentOutputDto> getStudentList();

    StudentOutputDto getStudentById(Long studentId);

    StudentOutputDto createStudent(StudentInputDto studentDto);

    StudentOutputDto updateStudentInfo(Long id, StudentInputDto studentDto);

    void deleteStudent(Long id);

    List<StudentOutputDto> getBySchoolId(Long schoolId);

    List<StudentOutputDto> getByGrade(Grade grade);

    List<StudentOutputDto> getByMarks(Long marks);

    List<StudentMarksDto> getByMarksRange(Long lower, Long higher);
}
