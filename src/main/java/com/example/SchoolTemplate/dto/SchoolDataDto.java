package com.example.SchoolTemplate.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolDataDto {
    private String schoolName;

    private String location;
}
