package com.example.SchoolTemplate.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolDto {
    private Long id;

    private String schoolName;

    private String location;
    
    private Integer year;
}
