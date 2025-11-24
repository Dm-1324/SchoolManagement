package com.example.SchoolTemplate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "school", uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_school_data",
                columnNames = {"school_name", "location"}
        )
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be empty")
    private String schoolName;

    @NotNull(message = "Location cannot be empty")
    private String location;

    @NotNull(message = "Year cannot be empty")
    private Integer year;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Sports> sports = new ArrayList<>();
}
