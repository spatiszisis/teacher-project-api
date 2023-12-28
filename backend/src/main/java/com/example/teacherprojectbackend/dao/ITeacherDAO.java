package com.example.teacherprojectbackend.dao;

import com.example.teacherprojectbackend.dto.TeacherDTO;
import com.example.teacherprojectbackend.model.Teacher;
import com.example.teacherprojectbackend.service.exceptions.TeacherIdAlreadyExistsException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITeacherDAO {
    TeacherDTO insertTeacher(TeacherDTO teacherDTO) throws TeacherIdAlreadyExistsException, RuntimeException;
    TeacherDTO updateTeacher(TeacherDTO updateTeacherDTO, String email);
    void deleteTeacher(String email);
    Page<TeacherDTO> findAllTeacher(Integer pageNumber, Integer pageSize, String sortProperty, String sortDirection);
    List<TeacherDTO> searchTeacher(String name);
    TeacherDTO findTeacherByEmail(String email);
}
