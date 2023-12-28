package com.example.teacherprojectbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;
    @Column(name = "lesson_name", nullable = false, length = 100)
    private String name;

    @OneToOne(mappedBy = "teacherLesson")
    @JsonIgnore
    private Teacher teacher;

    public Lesson() {}

    public Lesson(String name, Teacher teacher) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
