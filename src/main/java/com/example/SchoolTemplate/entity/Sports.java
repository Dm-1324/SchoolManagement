package com.example.SchoolTemplate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sports")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Sport name cannot be empty")
    private String name;

    private String coachName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToMany(mappedBy = "sports")
    private Set<Student> students = new HashSet<>();
}