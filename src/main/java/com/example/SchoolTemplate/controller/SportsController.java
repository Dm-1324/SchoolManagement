package com.example.SchoolTemplate.controller;

import com.example.SchoolTemplate.dto.sportsDto.SportsInputDto;
import com.example.SchoolTemplate.dto.sportsDto.SportsOutputDto;
import com.example.SchoolTemplate.service.SportsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
@AllArgsConstructor
public class SportsController {

    private final SportsService sportsService;

    @PostMapping
    public ResponseEntity<SportsOutputDto> createSport(@Valid @RequestBody SportsInputDto sportDto) {
        SportsOutputDto createdSport = sportsService.createSport(sportDto);
        return new ResponseEntity<>(createdSport, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<SportsOutputDto>> getAllSports() {
        List<SportsOutputDto> sports = sportsService.getAllSports();
        return ResponseEntity.ok(sports);
    }


    @GetMapping("{id}")
    public ResponseEntity<SportsOutputDto> getSportById(@PathVariable("id") Long id) {
        SportsOutputDto sportDto = sportsService.getSportById(id);
        return ResponseEntity.ok(sportDto);
    }


    @PutMapping("{id}")
    public ResponseEntity<SportsOutputDto> updateSport(@PathVariable("id") Long id,
                                                       @Valid @RequestBody SportsInputDto sportDto) {
        SportsOutputDto updatedSport = sportsService.updateSport(id, sportDto);
        return ResponseEntity.ok(updatedSport);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSport(@PathVariable("id") Long id) {
        sportsService.deleteSport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<SportsOutputDto>> getSportsBySchool(@PathVariable("schoolId") Long schoolId) {
        List<SportsOutputDto> sports = sportsService.getSportsBySchool(schoolId);
        return ResponseEntity.ok(sports);
    }
}