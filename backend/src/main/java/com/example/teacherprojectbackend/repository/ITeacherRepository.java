package com.example.teacherprojectbackend.repository;

import com.example.teacherprojectbackend.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findTeacherByEmail(String email);
    @Query("select user from Teacher user " +
            "where lower(user.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(user.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Teacher> search(@Param("searchTerm") String searchTerm);
}
