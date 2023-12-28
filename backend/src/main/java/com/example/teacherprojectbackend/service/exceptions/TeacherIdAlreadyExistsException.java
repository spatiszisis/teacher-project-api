package com.example.teacherprojectbackend.service.exceptions;

import com.example.teacherprojectbackend.dto.TeacherDTO;

public class TeacherIdAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public TeacherIdAlreadyExistsException(TeacherDTO teacherDTO) {
		super("Teacher with email = " + teacherDTO.getEmail() + " already exist");
	}

}