package com.example.teacherprojectbackend.service;

import com.example.teacherprojectbackend.converter.TeacherConverter;
import com.example.teacherprojectbackend.dao.ITeacherDAO;
import com.example.teacherprojectbackend.dto.TeacherDTO;
import com.example.teacherprojectbackend.model.Teacher;
import com.example.teacherprojectbackend.repository.ITeacherRepository;
import com.example.teacherprojectbackend.service.exceptions.TeacherIdAlreadyExistsException;
import com.example.teacherprojectbackend.service.exceptions.TeacherNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService implements ITeacherDAO {
    private final ITeacherRepository teacherRepository;
    private final TeacherConverter teacherConverter;

    public TeacherService(ITeacherRepository teacherRepository, TeacherConverter teacherConverter) {
        this.teacherRepository = teacherRepository;
        this.teacherConverter = teacherConverter;
    }

    @Override
    public TeacherDTO insertTeacher(TeacherDTO teacherDTO) throws TeacherIdAlreadyExistsException, RuntimeException {
        Teacher newTeacher = teacherConverter.convertToTeacher(teacherDTO);
        if (findTeacher(teacherDTO.getEmail()).isPresent()) {
            throw new TeacherIdAlreadyExistsException(teacherDTO);
        }

        return Optional.of(teacherRepository.save(newTeacher))
                .map(teacherConverter::convertToTeacherDTO)
                .orElseThrow(() -> new RuntimeException("Could not create the teacher."));
    }

    @Override
    public TeacherDTO updateTeacher(TeacherDTO updateTeacherDTO, String email) throws RuntimeException {
        Teacher existingTeacher = findTeacher(email).orElseThrow(() -> new TeacherNotFoundException(email));
        existingTeacher.setFirstName(updateTeacherDTO.getFirstName());
        existingTeacher.setLastName(updateTeacherDTO.getLastName());
        existingTeacher.setEmail(updateTeacherDTO.getEmail());
        existingTeacher.setGender(updateTeacherDTO.getGender());
        existingTeacher.setTeacherLesson(updateTeacherDTO.getTeacherLesson());
        return Optional.of(teacherRepository.save(existingTeacher))
                .map(teacherConverter::convertToTeacherDTO)
                .orElseThrow(() -> new RuntimeException("Could not update the teacher."));
    }

    @Override
    public void deleteTeacher(String email) throws TeacherNotFoundException {
        Teacher existingTeacher = findTeacher(email)
                .orElseThrow(() -> new TeacherNotFoundException(email));
        teacherRepository.delete(existingTeacher);
    }

    @Override
    public Page<TeacherDTO> findAllTeacher(Integer pageNumber, Integer pageSize, String sortProperty, String sortDirection) {
        Pageable pageable = null;
        if (sortProperty != null && sortDirection != null && !sortDirection.equals("DEFAULT")) {
            pageable = PageRequest.of(pageNumber, pageSize, sortDirection.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortProperty);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return teacherRepository.findAll(pageable).map(teacherConverter::convertToTeacherDTO);
    }

    @Override
    public List<TeacherDTO> searchTeacher(String filterText) {
        return teacherRepository.search(filterText).stream()
                .map(teacherConverter::convertToTeacherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO findTeacherByEmail(String email) throws TeacherNotFoundException {
        return findTeacher(email)
                .map(teacherConverter::convertToTeacherDTO)
                .orElseThrow(() -> new TeacherNotFoundException(email));
    }

    private Optional<Teacher> findTeacher(String email) {
        return teacherRepository.findTeacherByEmail(email);
    }
}
