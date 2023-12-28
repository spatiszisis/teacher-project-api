package com.example.teacherprojectbackend.converter;

import com.example.teacherprojectbackend.dto.TeacherDTO;
import com.example.teacherprojectbackend.model.Teacher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TeacherConverter {
    public Teacher convertToTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setTeacherLesson(teacherDTO.getTeacherLesson());
        teacher.setGender(teacherDTO.getGender());
        teacher.setCreateTime(LocalDateTime.now());
        return teacher;
    }

    public TeacherDTO convertToTeacherDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setTeacherLesson(teacher.getTeacherLesson());
        teacherDTO.setGender(teacher.getGender());
        teacherDTO.setCreateTime(LocalDateTime.now());
        return teacherDTO;
    }

}
