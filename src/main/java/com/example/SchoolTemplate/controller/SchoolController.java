package com.example.SchoolTemplate.controller;

import com.example.SchoolTemplate.dto.SchoolDto;
import com.example.SchoolTemplate.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@AllArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<SchoolDto>> getAllSchools() {
        List<SchoolDto> schools = schoolService.getSchoolList();
        return ResponseEntity.ok(schools);
    }


    @GetMapping("{id}")
    public ResponseEntity<SchoolDto> getSchoolById(@PathVariable("id") Long id) {
        SchoolDto schoolDto = schoolService.getSchoolById(id);
        return ResponseEntity.ok(schoolDto);
    }


    @PostMapping
    public ResponseEntity<SchoolDto> createSchool(@RequestBody SchoolDto schoolDto) {
        SchoolDto createdSchool = schoolService.createSchool(schoolDto);
        return new ResponseEntity<>(createdSchool, HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<SchoolDto> updateSchool(@PathVariable("id") Long id,
                                                  @RequestBody SchoolDto schoolDto) {
        SchoolDto updatedSchool = schoolService.updateSchoolInfo(id, schoolDto);
        return ResponseEntity.ok(updatedSchool);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable("id") Long id) {
        schoolService.deleteSchool(id);

        return ResponseEntity.noContent().build();
    }
}