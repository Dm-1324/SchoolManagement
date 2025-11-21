package com.example.SchoolTemplate.service;

import com.example.SchoolTemplate.dto.StudentInputDto;
import com.example.SchoolTemplate.dto.StudentOutputDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<StudentOutputDto> getStudentList();

    StudentOutputDto getStudentById(Long studentId);

    StudentOutputDto createStudent(StudentInputDto studentDto);

    StudentOutputDto updateStudentInfo(Long id, StudentInputDto studentDto);

    void deleteStudent(Long id);
}
