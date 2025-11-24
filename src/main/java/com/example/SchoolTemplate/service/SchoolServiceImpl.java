package com.example.SchoolTemplate.service;

import com.example.SchoolTemplate.dto.schoolDto.SchoolAverageView;
import com.example.SchoolTemplate.dto.schoolDto.SchoolDto;
import com.example.SchoolTemplate.dto.schoolDto.SchoolInputDto;
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
    public SchoolInputDto createSchool(SchoolInputDto schoolDto) {
        School school = schoolMapper.toEntity(schoolDto);
        return schoolMapper.toInputDto(schoolRepository.save(school));
    }

    @Override
    public SchoolDto updateSchoolInfo(Long id, SchoolInputDto schoolDto) {
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

    @Override
    public Long countStudentBySchoolId(Long schoolId) {
        return schoolRepository.countStudentsBySchoolId(schoolId);
    }

    @Override
    public List<SchoolAverageView> getAverageBySchool() {
        return schoolRepository.getAverageMark();
    }
}