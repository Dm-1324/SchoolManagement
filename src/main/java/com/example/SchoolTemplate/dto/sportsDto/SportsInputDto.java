package com.example.SchoolTemplate.dto.sportsDto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportsInputDto {

    @NotNull(message = "Sport name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    private String coachName;

    @NotNull(message = "School ID is required for the sport")
    private Long schoolId;

    private Set<Long> studentIds;
}