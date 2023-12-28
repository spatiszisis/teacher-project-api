package com.example.teacherprojectbackend.dto;

import com.example.teacherprojectbackend.model.Gender;
import com.example.teacherprojectbackend.model.Lesson;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class TeacherDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Lesson teacherLesson;
    private Gender gender;
    private LocalDateTime createTime;

    public TeacherDTO() {
    }

    public TeacherDTO(String firstName, String lastName, String email, Lesson teacherLesson, Gender gender, LocalDateTime createTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.teacherLesson = teacherLesson;
        this.gender = gender;
        this.createTime = createTime;
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
}
