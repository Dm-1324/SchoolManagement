package com.example.SchoolTemplate.mapper;

import com.example.SchoolTemplate.dto.SchoolDataDto;
import com.example.SchoolTemplate.dto.SchoolDto;
import com.example.SchoolTemplate.entity.School;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

    public static SchoolDataDto toDataDto(School school) {

        return SchoolDataDto.builder()
                .schoolName(school.getSchoolName())
                .location(school.getLocation())
                .build();
    }

    public School toEntity(SchoolDto dto) {
        return School.builder()
                .id(dto.getId())
                .schoolName(dto.getSchoolName())
                .location(dto.getLocation())
                .year(dto.getYear())
                .build();
    }

    public SchoolDto toDto(School school) {
        return SchoolDto.builder()
                .id(school.getId())
                .schoolName(school.getSchoolName())
                .location(school.getLocation())
                .year(school.getYear())
                .build();
    }

    public void updateEntityFromDTO(SchoolDto dto, School school) {
        school.setSchoolName(dto.getSchoolName());
        school.setLocation(dto.getLocation());
        school.setYear(dto.getYear());
    }
}
