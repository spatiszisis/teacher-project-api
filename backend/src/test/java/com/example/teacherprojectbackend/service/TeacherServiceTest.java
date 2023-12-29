package com.example.teacherprojectbackend.service;

import com.example.teacherprojectbackend.converter.TeacherConverter;
import com.example.teacherprojectbackend.repository.ITeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.verify;

class TeacherServiceTest {

    private TeacherService underTest;
    private AutoCloseable autoCloseable;
    @Mock
    private ITeacherRepository teacherRepository;
    @Mock
    private TeacherConverter teacherConverter;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new TeacherService(teacherRepository, teacherConverter);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findAllTeacher_select_all_teachers() {
        int pageNumber = 0;
        int pageSize = 4;
        underTest.findAllTeacher(pageNumber, pageSize, null, null);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        verify(teacherRepository).findAll(pageable).map(teacherConverter::convertToTeacherDTO);
    }

    @Test
    void findTeacherByEmail_existing_email() {
        String email = "zisis@gmail.com";
        underTest.findTeacherByEmail(email);
        verify(teacherRepository).findTeacherByEmail(email).map(teacherConverter::convertToTeacherDTO);
    }

}