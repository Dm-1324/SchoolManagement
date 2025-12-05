package com.example.SchoolTemplate.service;


import com.example.SchoolTemplate.dto.sportsDto.SportsInputDto;
import com.example.SchoolTemplate.dto.sportsDto.SportsOutputDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SportsService {
    SportsOutputDto createSport(SportsInputDto sportDto);

    SportsOutputDto getSportById(Long sportId);

    List<SportsOutputDto> getAllSports();

    SportsOutputDto updateSport(Long sportId, SportsInputDto sportDto);

    void deleteSport(Long sportId);

    List<SportsOutputDto> getSportsBySchool(Long schoolId);
}