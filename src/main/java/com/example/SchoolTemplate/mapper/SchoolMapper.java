package com.example.SchoolTemplate.mapper;

import com.example.SchoolTemplate.dto.schoolDto.SchoolDataDto;
import com.example.SchoolTemplate.dto.schoolDto.SchoolDto;
import com.example.SchoolTemplate.dto.schoolDto.SchoolInputDto;
import com.example.SchoolTemplate.entity.School;
import com.example.SchoolTemplate.entity.Sports;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SchoolMapper {

    public static SchoolDataDto toDataDto(School school) {

        return SchoolDataDto.builder()
                .schoolName(school.getSchoolName())
                .location(school.getLocation())
                .build();
    }

    public School toEntity(SchoolInputDto dto) {
        return School.builder()
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

    public SchoolInputDto toInputDto(School school) {
        return SchoolInputDto.builder()
                .schoolName(school.getSchoolName())
                .location(school.getLocation())
                .year(school.getYear())
                .sportIds(school.getSports().stream().map(Sports::getId).collect(Collectors.toList()))
                .build();
    }

    public void updateEntityFromDTO(SchoolInputDto dto, School school) {
        school.setSchoolName(dto.getSchoolName());
        school.setLocation(dto.getLocation());
        school.setYear(dto.getYear());
    }
}
