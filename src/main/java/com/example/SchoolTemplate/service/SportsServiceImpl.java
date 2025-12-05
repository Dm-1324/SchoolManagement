package com.example.SchoolTemplate.service;

import com.example.SchoolTemplate.dto.sportsDto.SportsInputDto;
import com.example.SchoolTemplate.dto.sportsDto.SportsOutputDto;
import com.example.SchoolTemplate.entity.Sports;
import com.example.SchoolTemplate.exception.ResourceNotFoundException;
import com.example.SchoolTemplate.mapper.SportsMapper;
import com.example.SchoolTemplate.repository.SportsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SportsServiceImpl implements SportsService {

    private final SportsRepository sportsRepository;
    private final SportsMapper sportsMapper;

    @Override
    public SportsOutputDto createSport(SportsInputDto sportDto) {
        Sports sports = sportsMapper.toEntity(sportDto);
        Sports savedSports = sportsRepository.save(sports);
        return sportsMapper.toDto(savedSports);
    }

    @Override
    public SportsOutputDto getSportById(Long sportId) {
        Sports sports = sportsRepository.findById(sportId)
                .orElseThrow(() -> new ResourceNotFoundException("Sport", "id", sportId));
        return sportsMapper.toDto(sports);
    }

    @Override
    public List<SportsOutputDto> getAllSports() {
        return sportsRepository.findAll().stream()
                .map(sportsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SportsOutputDto updateSport(Long sportId, SportsInputDto sportDto) {
        Sports existingSports = sportsRepository.findById(sportId)
                .orElseThrow(() -> new ResourceNotFoundException("Sport", "id", sportId));

        sportsMapper.updateEntityFromDTO(sportDto, existingSports);

        Sports updatedSports = sportsRepository.save(existingSports);
        return sportsMapper.toDto(updatedSports);
    }

    @Override
    public void deleteSport(Long sportId) {
        Sports sports = sportsRepository.findById(sportId)
                .orElseThrow(() -> new ResourceNotFoundException("Sport", "id", sportId));

        sportsRepository.delete(sports);
    }

    @Override
    public List<SportsOutputDto> getSportsBySchool(Long schoolId) {
        return sportsRepository.findBySchoolId(schoolId).stream()
                .map(sportsMapper::toDto)
                .collect(Collectors.toList());
    }
}