package com.example.SchoolTemplate.service;

import com.example.SchoolTemplate.dto.SchoolDto;
import com.example.SchoolTemplate.entity.School;
import com.example.SchoolTemplate.exception.ResourceNotFoundException;
import com.example.SchoolTemplate.mapper.SchoolMapper;
import com.example.SchoolTemplate.repository.SchoolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public List<SchoolDto> getSchoolList() {
        return schoolRepository.findAll()
                .stream()
                .map(s -> schoolMapper.toDto(s))
                .toList();
    }

    @Override
    public SchoolDto getSchoolById(Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", schoolId));
        return schoolMapper.toDto(school);
    }

    @Override
    public SchoolDto createSchool(SchoolDto schoolDto) {
        School school = schoolMapper.toEntity(schoolDto);
        return schoolMapper.toDto(schoolRepository.save(school));
    }

    @Override
    public SchoolDto updateSchoolInfo(Long id, SchoolDto schoolDto) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", id));

        schoolMapper.updateEntityFromDTO(schoolDto, school);

        return schoolMapper.toDto(schoolRepository.save(school));
    }

    @Override
    public void deleteSchool(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", id));
        schoolRepository.delete(school);

    }
}