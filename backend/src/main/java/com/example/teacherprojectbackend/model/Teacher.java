package com.example.teacherprojectbackend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;
    @Column(name = "firstName", nullable = false, length = 100)
    private String firstName;
    @Column(name = "lastName", nullable = false, length = 100)
    private String lastName;
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;
    @Column(name = "create_time", nullable = false, length = 100)
    private LocalDateTime createTime;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "lesson_id")
    private Lesson teacherLesson;

    public Teacher() {}

    public Teacher(String firstName, String lastName, String email, Lesson teacherLesson, Gender gender, LocalDateTime createTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.teacherLesson = teacherLesson;
        this.gender = gender;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Lesson getTeacherLesson() {
        return teacherLesson;
    }

    public void setTeacherLesson(Lesson teacherLesson) {
        this.teacherLesson = teacherLesson;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", teacherLesson=" + teacherLesson +
                ", gender=" + gender +
                ", createTime=" + createTime +
                '}';
    }
}