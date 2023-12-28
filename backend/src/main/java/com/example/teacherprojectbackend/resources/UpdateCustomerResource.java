package com.example.teacherprojectbackend.resources;

import com.example.teacherprojectbackend.dto.TeacherDTO;

public class UpdateCustomerResource {

    private TeacherDTO teacherDTO;
    public TeacherDTO getTeacherDTO() { return teacherDTO; }
    public void setTeacherDTO(TeacherDTO teacherDTO) { this.teacherDTO = teacherDTO; }

    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
