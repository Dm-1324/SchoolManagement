package com.example.SchoolTemplate.controller;

import com.example.SchoolTemplate.dto.StudentInputDto;
import com.example.SchoolTemplate.dto.StudentOutputDto;
import com.example.SchoolTemplate.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping
    public ResponseEntity<List<StudentOutputDto>> getAllStudents() {
        List<StudentOutputDto> students = studentService.getStudentList();
        return ResponseEntity.ok(students);
    }


    @GetMapping("{id}")
    public ResponseEntity<StudentOutputDto> getStudentById(@PathVariable("id") Long id) {
        StudentOutputDto studentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDto);
    }


    @PostMapping
    public ResponseEntity<StudentOutputDto> createStudent(@RequestBody StudentInputDto studentDto) {
        StudentOutputDto createdStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentOutputDto> updateStudent(@PathVariable("id") Long id,
                                                          @RequestBody StudentInputDto studentDto) {
        StudentOutputDto updatedStudent = studentService.updateStudentInfo(id, studentDto);
        return ResponseEntity.ok(updatedStudent);
    }
    

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}