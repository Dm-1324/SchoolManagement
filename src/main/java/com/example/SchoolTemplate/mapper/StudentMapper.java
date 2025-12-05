package com.example.SchoolTemplate.mapper;

import com.example.SchoolTemplate.dto.studentDto.StudentDto;
import com.example.SchoolTemplate.dto.studentDto.StudentInputDto;
import com.example.SchoolTemplate.dto.studentDto.StudentMarksDto;
import com.example.SchoolTemplate.dto.studentDto.StudentOutputDto;
import com.example.SchoolTemplate.entity.School;
import com.example.SchoolTemplate.entity.Sports;
import com.example.SchoolTemplate.entity.Student;
import com.example.SchoolTemplate.repository.SchoolRepository;
import com.example.SchoolTemplate.repository.SportsRepository;
import com.example.SchoolTemplate.utils.GradeCalculator;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    private final SchoolRepository schoolRepository;
    private final SportsRepository sportsRepository;

    public StudentMapper(SchoolRepository schoolRepository, SportsRepository sportsRepository) {
        this.schoolRepository = schoolRepository;
        this.sportsRepository = sportsRepository;
    }

    private Set<Sports> getSportsFromIds(Set<Long> sportIds) {
        if (sportIds == null || sportIds.isEmpty()) {
            return null;
        }
        return sportsRepository.findAllById(sportIds).stream().collect(Collectors.toSet());
    }

    public Student toEntity(StudentInputDto dto) {

        School school = schoolRepository.findById(dto.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found"));

        Set<Sports> sports = getSportsFromIds(dto.getSportIds());

        return Student.builder()
                .name(dto.getName())
                .grade(GradeCalculator.calculateGrade(dto.getMarks()))
                .marks(dto.getMarks())
                .school(school)
                .sports(sports)
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
                .sportIds(student.getSports().stream().map(Sports::getId).collect(Collectors.toSet()))
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

        Set<Sports> newSports = getSportsFromIds(dto.getSportIds());

        student.setName(dto.getName());
        student.setMarks(dto.getMarks());
        student.setSchool(school);
        student.setSports(newSports);
    }
}