package com.example.SchoolTemplate.dto.schoolDto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolInputDto {
    private String schoolName;

    private String location;

    private Integer year;
    private List<Long> sportIds;

}
