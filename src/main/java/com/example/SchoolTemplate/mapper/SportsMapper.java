package com.example.SchoolTemplate.mapper;

import com.example.SchoolTemplate.dto.schoolDto.SchoolDataDto;
import com.example.SchoolTemplate.dto.sportsDto.SportsInputDto;
import com.example.SchoolTemplate.dto.sportsDto.SportsOutputDto;
import com.example.SchoolTemplate.entity.School;
import com.example.SchoolTemplate.entity.Sports;
import com.example.SchoolTemplate.entity.Student;
import com.example.SchoolTemplate.exception.ResourceNotFoundException;
import com.example.SchoolTemplate.repository.SchoolRepository;
import com.example.SchoolTemplate.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SportsMapper {

    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;

    public SportsMapper(SchoolRepository schoolRepository, StudentRepository studentRepository) {
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
    }

    private SchoolDataDto mapSchoolToDataDto(School school) {
        return SchoolMapper.toDataDto(school);
    }

    private Set<Student> getStudentsFromIds(Set<Long> studentIds) {
        if (studentIds == null || studentIds.isEmpty()) {
            return new HashSet<>();
        }
        return studentRepository.findAllById(studentIds).stream().collect(Collectors.toSet());
    }

    public Sports toEntity(SportsInputDto dto) {
        School school = schoolRepository.findById(dto.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", dto.getSchoolId()));

        Set<Student> students = getStudentsFromIds(dto.getStudentIds());

        return Sports.builder()
                .name(dto.getName())
                .coachName(dto.getCoachName())
                .school(school)
                .students(students)
                .build();
    }

    public SportsOutputDto toDto(Sports sports) {
        return SportsOutputDto.builder()
                .id(sports.getId())
                .name(sports.getName())
                .coachName(sports.getCoachName())
                .schoolData(mapSchoolToDataDto(sports.getSchool()))
                .studentIds(sports.getStudents().stream().map(Student::getId).collect(Collectors.toSet()))
                .build();
    }

    public void updateEntityFromDTO(SportsInputDto dto, Sports sports) {
        sports.setName(dto.getName());
        sports.setCoachName(dto.getCoachName());


        if (!sports.getSchool().getId().equals(dto.getSchoolId())) {
            School school = schoolRepository.findById(dto.getSchoolId())
                    .orElseThrow(() -> new ResourceNotFoundException("School", "id", dto.getSchoolId()));
            sports.setSchool(school);
        }

        Set<Student> updatedStudents = getStudentsFromIds(dto.getStudentIds());
        sports.setStudents(updatedStudents);
    }
}