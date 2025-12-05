package com.example.SchoolTemplate.controller;

import com.example.SchoolTemplate.dto.studentDto.StudentInputDto;
import com.example.SchoolTemplate.dto.studentDto.StudentMarksDto;
import com.example.SchoolTemplate.dto.studentDto.StudentOutputDto;
import com.example.SchoolTemplate.enums.Grade;
import com.example.SchoolTemplate.service.StudentService;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<Page<StudentOutputDto>> getStudentBySchoolId(
            @PathVariable Long schoolId,
            // Uses Pageable to handle ?page, ?size, and ?sort parameters from the URL
            @ParameterObject
            @PageableDefault(size = 10, page = 0, sort = "marks", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable
    ) {
        Page<StudentOutputDto> studentList = studentService.getBySchoolId(schoolId, pageable);

        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<StudentOutputDto>> getStudentByGrade(@PathVariable Grade grade) {
        List<StudentOutputDto> studentList = studentService.getByGrade(grade);

        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/marks")
    public ResponseEntity<List<StudentOutputDto>> getStudentByMarksGreaterThan(@RequestParam Long marks) {
        List<StudentOutputDto> studentList = studentService.getByMarks(marks);

        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/marks/inRange")
    public ResponseEntity<List<StudentMarksDto>> getStudentsByRange(@RequestParam Long lower, @RequestParam Long higher) {
        List<StudentMarksDto> studentMarksList = studentService.getByMarksRange(lower, higher);

        return ResponseEntity.ok(studentMarksList);
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