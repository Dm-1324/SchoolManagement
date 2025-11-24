package com.example.SchoolTemplate.service;

import com.example.SchoolTemplate.dto.studentDto.StudentInputDto;
import com.example.SchoolTemplate.dto.studentDto.StudentMarksDto;
import com.example.SchoolTemplate.dto.studentDto.StudentOutputDto;
import com.example.SchoolTemplate.entity.School;
import com.example.SchoolTemplate.entity.Student;
import com.example.SchoolTemplate.enums.Grade;
import com.example.SchoolTemplate.exception.ResourceNotFoundException;
import com.example.SchoolTemplate.mapper.StudentMapper;
import com.example.SchoolTemplate.repository.SchoolRepository;
import com.example.SchoolTemplate.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<StudentOutputDto> getStudentList() {
        return studentRepository.findAll()
                .stream()
                .map(s -> studentMapper.toOutputDto(s))
                .toList();
    }

    @Override
    public StudentOutputDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        return studentMapper.toOutputDto(student);
    }

    @Override
    public StudentOutputDto createStudent(StudentInputDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        return studentMapper.toOutputDto(studentRepository.save(student));
    }

    @Override
    public StudentOutputDto updateStudentInfo(Long id, StudentInputDto studentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        studentMapper.updateEntityFromDTO(studentDto, student);

        return studentMapper.toOutputDto(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        studentRepository.delete(student);

    }

    @Override
    public List<StudentOutputDto> getBySchoolId(Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", schoolId));

        List<Student> students = studentRepository.findBySchoolId(schoolId);

        return students.stream()
                .map(studentMapper::toOutputDto)
                .toList();
    }

    @Override
    public List<StudentOutputDto> getByGrade(Grade grade) {
        List<Student> students = studentRepository.findByGrade(grade);

        return students.stream()
                .map(studentMapper::toOutputDto)
                .toList();
    }

    @Override
    public List<StudentOutputDto> getByMarks(Long marks) {
        List<Student> students = studentRepository.findByMarksGreaterThan(marks);

        return students.stream()
                .map(studentMapper::toOutputDto)
                .toList();

    }

    @Override
    public List<StudentMarksDto> getByMarksRange(Long lower, Long higher) {
        List<Student> students = studentRepository.findMarksInRange(lower, higher);

        return students.stream()
                .map(studentMapper::toMarksDto)
                .toList();
    }
}