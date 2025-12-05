package com.example.SchoolTemplate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Field cannot be empty")
    private String firstName;

    @NotNull(message = "Field cannot be empty")
    private String lastName;

    @NotNull(message = "Field cannot be empty")
    private String specialty;

    @OneToOne(mappedBy = "teacher")
    private Course course;
}
