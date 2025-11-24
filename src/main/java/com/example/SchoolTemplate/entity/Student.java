package com.example.SchoolTemplate.entity;

import com.example.SchoolTemplate.enums.Grade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Marks cannot be empty")
    @Min(value = 0, message = "Marks Cannot be less than 0")
    @Max(value = 100, message = "Marks cannot be more than 100")
    private Long marks;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Grade cannot be empty")
    private Grade grade;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToMany
    @JoinTable(name = "student_sport",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id"))
    private Set<Sports> sports = new HashSet<>();

}
