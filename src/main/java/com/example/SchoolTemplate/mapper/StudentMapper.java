package com.example.SchoolTemplate.mapper;

import com.example.SchoolTemplate.dto.StudentDto;
import com.example.SchoolTemplate.dto.StudentInputDto;
import com.example.SchoolTemplate.dto.StudentMarksDto;
import com.example.SchoolTemplate.dto.StudentOutputDto;
import com.example.SchoolTemplate.entity.School;
import com.example.SchoolTemplate.entity.Student;
import com.example.SchoolTemplate.repository.SchoolRepository;
import com.example.SchoolTemplate.utils.GradeCalculator;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private final SchoolRepository schoolRepository;

    public StudentMapper(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public Student toEntity(StudentInputDto dto) {

        School school = schoolRepository.findById(dto.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found"));

        return Student.builder()
                .name(dto.getName())
                .grade(GradeCalculator.calculateGrade(dto.getMarks()))
                .marks(dto.getMarks())
                .school(school)
                .build();
    }

    public StudentDto toDto(Student student) {


        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .marks(student.getMarks())
                .grade(GradeCalculator.calculateGrade(student.getMarks()))
                .schoolId(student.getSchool().getId())
                .build();
    }

    public StudentOutputDto toOutputDto(Student student) {


        return StudentOutputDto.builder()
                .id(student.getId())
                .name(student.getName())
                .marks(student.getMarks())
                .grade(GradeCalculator.calculateGrade(student.getMarks()))
                .schoolData(SchoolMapper.toDataDto(student.getSchool()))
                .build();
    }

    public StudentMarksDto toMarksDto(Student student) {
        return StudentMarksDto.builder()
                .name(student.getName())
                .marks(student.getMarks())
                .build();
    }

    public void updateEntityFromDTO(StudentInputDto dto, Student student) {
        School school = schoolRepository.findById(dto.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found"));

        student.setName(dto.getName());
        student.setMarks(dto.getMarks());
        student.setSchool(school);
    }
}
