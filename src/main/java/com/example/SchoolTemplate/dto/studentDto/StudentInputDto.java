package com.example.SchoolTemplate.dto.studentDto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentInputDto {
    private String name;
    private Long marks;
    private Long schoolId;

    private Set<Long> sportIds;
}
