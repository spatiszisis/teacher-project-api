package com.example.teacherprojectbackend.controller;

import com.example.teacherprojectbackend.dto.TeacherDTO;
import com.example.teacherprojectbackend.resources.UpdateCustomerResource;
import com.example.teacherprojectbackend.service.TeacherService;
import com.example.teacherprojectbackend.service.exceptions.TeacherIdAlreadyExistsException;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("all")
    public ResponseEntity<Page<TeacherDTO>> getTeachers(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @RequestParam(value = "sortProperty", required = false) String sortProperty,
            @RequestParam(value = "sortDirection", required = false) String sortDirection) {
        return ResponseEntity.ok(teacherService.findAllTeacher(pageNumber, pageSize, sortProperty, sortDirection));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TeacherDTO>> searchTeacher(@RequestParam(value = "searchTerm", required = true) String searchTerm) {
        return ResponseEntity.ok(teacherService.searchTeacher(searchTerm));
    }

    @GetMapping()
    public ResponseEntity getTeacher(
            @RequestParam(value = "email", required = true) String email
    ) {
        return ResponseEntity.ok(teacherService.findTeacherByEmail(email));
    }

    @PostMapping()
    public ResponseEntity createTeacher(@RequestBody TeacherDTO teacherDTO) throws TeacherIdAlreadyExistsException {
        return ResponseEntity.ok(teacherService.insertTeacher(teacherDTO));
    }

    @PutMapping()
    public ResponseEntity updateTeacher(@RequestBody UpdateCustomerResource updateCustomerResource) {
        return ResponseEntity.ok(teacherService.updateTeacher(updateCustomerResource.getTeacherDTO(), updateCustomerResource.getEmail()));
    }

    @DeleteMapping()
    public ResponseEntity deleteTeacher(
            @RequestParam(value = "email", required = true) String email
    ) {
        teacherService.deleteTeacher(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
