package com.example.SchoolTemplate.service;

import com.example.SchoolTemplate.dto.StudentInputDto;
import com.example.SchoolTemplate.dto.StudentOutputDto;
import com.example.SchoolTemplate.entity.Student;
import com.example.SchoolTemplate.mapper.StudentMapper;
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
                .orElseThrow(() -> new RuntimeException("Student Not Found"));
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
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        studentMapper.updateEntityFromDTO(studentDto, student);

        return studentMapper.toOutputDto(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        studentRepository.delete(student);

    }
}
