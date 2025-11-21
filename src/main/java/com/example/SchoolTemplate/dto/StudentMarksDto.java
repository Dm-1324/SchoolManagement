package com.example.SchoolTemplate.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentMarksDto {
    private String name;
    private Long marks;
}
