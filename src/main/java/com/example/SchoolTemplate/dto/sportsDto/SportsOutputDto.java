package com.example.SchoolTemplate.dto.sportsDto;

import com.example.SchoolTemplate.dto.schoolDto.SchoolDataDto;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportsOutputDto {
    private Long id;
    private String name;
    private String coachName;
    private SchoolDataDto schoolData;


    private Set<Long> studentIds;
}