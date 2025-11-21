package com.example.SchoolTemplate.dto;

import com.example.SchoolTemplate.enums.Grade;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String name;
    private Long marks;
    private Grade grade;
    private Long schoolId;
}
