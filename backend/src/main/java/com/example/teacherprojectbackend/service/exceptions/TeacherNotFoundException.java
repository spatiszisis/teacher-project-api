package com.example.teacherprojectbackend.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public TeacherNotFoundException(String email) {
        super("Teacher with email = " + email + " does no exist");
    }


}
